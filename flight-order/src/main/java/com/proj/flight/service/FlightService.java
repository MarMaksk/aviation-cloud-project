package com.proj.flight.service;

import com.proj.flight.dto.FlightDTO;
import com.proj.flight.entity.Flight;
import com.proj.flight.entity.enums.FlightStatus;
import com.proj.flight.exception.NoSuchAirplaneException;
import com.proj.flight.exception.NoSuchAirportException;
import com.proj.flight.exception.NoSuchFlightException;
import com.proj.flight.kafka.OrderKafkaProducer;
import com.proj.flight.repository.AirplaneRepository;
import com.proj.flight.repository.FlightRepository;
import com.proj.flight.service.mapper.FlightDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.entity.InfoForOrder;
import org.aviation.service.CRUD;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FlightService implements CRUD<FlightDTO> {

    FlightDTOMapper mapper;
    FlightRepository repository;
    AirplaneRepository airplaneRepository;
    ModelMapper modelMapper;
    OrderKafkaProducer sender;

    public void selectAlternativeFlight(String flightNumber, String flightNumberAlternative) throws NoSuchFlightException {
        Flight flight = repository.findByFlightNumber(flightNumber).orElseThrow(NoSuchFlightException::new);
        Flight altFlight = repository.findByFlightNumber(flightNumberAlternative).orElseThrow(NoSuchFlightException::new);
        flight.setAlternativeFlights(altFlight);
        repository.save(flight);
    }

    public List<FlightDTO> findAlternativeFlights(String flightNumber) throws NoSuchFlightException {
        Flight old = repository.findByFlightNumber(flightNumber).orElseThrow(NoSuchFlightException::new);
        List<Flight> allAlternativeFlights = repository.findAllAlternativeFlights(old.getDepartureAirport().getIcaoCode(),
                old.getArrivalAirport().getIcaoCode(),
                old.getPassengersCount(),
                old.getDeparture(),
                old.getFlightNumber());
        return mapper.toDTOs(allAlternativeFlights);
    }

    public List<FlightDTO> findAll() {
        List<Flight> all = repository.findAllByDeletedFalse();
        return mapper.toDTOs(all);
    }

    public void updateStatus(FlightDTO dto) throws NoSuchFlightException {
        Flight flight = repository.findByFlightNumber(dto.getFlightNumber()).orElseThrow(NoSuchFlightException::new);
        flight.setStatus(FlightStatus.fromString(dto.getStatus()));
        repository.save(flight);
    }

    @Override
    public void create(FlightDTO dto) throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        Flight flight = mapper.toEntity(dto);
        flight.getAirplane().setBusy(true);
        flight.setStatus(FlightStatus.CREATED);
        repository.save(flight);
        InfoForOrder info = new InfoForOrder(dto.getIcaoCodeDeparture(), dto.getIataCode(), dto.getDeparture(), flight.getProductOrderId());
        sender.send(info);
    }

    @Override
    public FlightDTO findByCode(String flightNumber) throws NoSuchFlightException {
        Flight flight = repository.findByFlightNumber(flightNumber).orElseThrow(NoSuchFlightException::new);
        return mapper.toDTO(flight);
    }


    @Override
    public FlightDTO update(FlightDTO dto) throws NoSuchFlightException, NoSuchAirplaneException, NoSuchAirportException {
        // Получаем наш старый полёт
        Flight flight = repository.findByFlightNumber(dto.getFlightNumber()).orElseThrow(NoSuchFlightException::new);
        // Собираем новый полёт по новым параметрам
        Flight entity = mapper.toEntity(dto);
        // Освобождаем старый самолёт
        flight.getAirplane().setBusy(false);
        // Обновляем информацию о нём
        airplaneRepository.save(flight.getAirplane());
        // Замещаем информацию о старом полёте новой информацией
        modelMapper.map(entity, flight);
        // Замаем новый самолёт
        flight.getAirplane().setBusy(true);
        // Сохраняем новый полёт
        return mapper.toDTO(repository.save(flight));
    }

    @Override
    public void delete(Long id) throws NoSuchFlightException {
        Flight flight = repository.findById(id).orElseThrow(NoSuchFlightException::new);
        flight.setDeleted(true);
        repository.save(flight);
    }
}
