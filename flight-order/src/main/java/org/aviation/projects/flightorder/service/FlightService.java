package org.aviation.projects.flightorder.service;

import org.aviation.projects.flightorder.dto.FlightDTO;
import org.aviation.projects.flightorder.entity.Flight;
import org.aviation.projects.flightorder.entity.enums.FlightStatus;
import org.aviation.projects.flightorder.exception.NoSuchAirplaneException;
import org.aviation.projects.flightorder.exception.NoSuchAirportException;
import org.aviation.projects.flightorder.exception.NoSuchFlightException;
import org.aviation.projects.flightorder.kafka.OrderKafkaProducer;
import org.aviation.projects.flightorder.repository.AirplaneRepository;
import org.aviation.projects.flightorder.repository.FlightRepository;
import org.aviation.projects.flightorder.service.mapper.FlightDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.entity.InfoForOrder;
import org.aviation.projects.commons.service.CRUD;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Flight> filtered = allAlternativeFlights.stream()
                        .filter(x -> x.getStatus() == FlightStatus.CREATED || x.getStatus() == FlightStatus.READY)
                        .collect(Collectors.toList());
        return mapper.toDTOs(filtered);
    }

    public List<FlightDTO> findAll() {
        List<Flight> all = repository.findAllByDeletedFalse();
        return mapper.toDTOs(all);
    }

    public void create(FlightDTO dto, String token) throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        Flight flight = mapper.toEntity(dto);
        flight.getAirplane().setBusy(true);
        flight.setStatus(FlightStatus.CREATED);
        repository.save(flight);
        InfoForOrder info = new InfoForOrder(dto.getIcaoCodeDeparture(), dto.getIataCode(), dto.getDeparture(), flight.getProductOrderId());
        sender.send(info, token);
    }

    @Override
    public void create(FlightDTO dto) throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        Flight flight = mapper.toEntity(dto);
        flight.getAirplane().setBusy(true);
        flight.setStatus(FlightStatus.CREATED);
        repository.save(flight);
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
        // Занимаем новый самолёт
        flight.getAirplane().setBusy(
                flight.getStatus() != FlightStatus.COMPLETED && flight.getStatus() != FlightStatus.CANCELLATION
        );
        // Сохраняем новый полёт
        return mapper.toDTO(repository.save(flight));
    }

    @Override
    public void delete(String flightNumber) throws NoSuchFlightException {
        Flight flight = repository.findByFlightNumber(flightNumber).orElseThrow(NoSuchFlightException::new);
        flight.setDeleted(true);
        repository.save(flight);
    }
}
