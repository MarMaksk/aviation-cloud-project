package com.proj.flight.service;

import com.proj.flight.dto.FlightDTO;
import com.proj.flight.entity.Flight;
import com.proj.flight.entity.enums.FlightStatus;
import com.proj.flight.exception.NoSuchAirplaneException;
import com.proj.flight.exception.NoSuchAirportException;
import com.proj.flight.exception.NoSuchFlightException;
import com.proj.flight.repository.AirplaneRepository;
import com.proj.flight.repository.FlightRepository;
import com.proj.flight.service.mapper.FlightDTOMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.entity.InfoForOrder;
import org.aviation.service.CRUD;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FlightService implements CRUD<FlightDTO> {

    FlightDTOMapper mapper;
    FlightRepository repository;
    AirplaneRepository airplaneRepository;
    ModelMapper modelMapper;
    InfoForOrderSender sender;


    public List<FlightDTO> findAll() {
        List<Flight> all = repository.findAllByDeletedFalse();
        return mapper.toDTOs(all);
    }

    @Override
    public void create(FlightDTO dto) throws NoSuchAirplaneException, NoSuchAirportException {
        Flight flight = mapper.toEntity(dto);
        flight.getAirplane().setBusy(true);
        flight.setStatus(FlightStatus.CREATED);
        repository.save(flight);
        InfoForOrder info = new InfoForOrder(dto.getIcaoCodeDeparture(), dto.getIataCode(), dto.getDeparture(), flight.getProductOrderId());
        System.out.println(info);
        sender.send(info);
    }

    @Override
    public FlightDTO findByCode(String flightNumber) throws NoSuchFlightException {
        Flight flight = repository.findByFlightNumber(flightNumber).orElseThrow(NoSuchFlightException::new);
        return mapper.toDTO(flight);
    }


    @Override
    public FlightDTO update(FlightDTO dto) throws NoSuchFlightException, NoSuchAirplaneException, NoSuchAirportException {
        Flight flight = repository.findByFlightNumber(dto.getFlightNumber()).orElseThrow(NoSuchFlightException::new);
        Flight entity = mapper.toEntity(dto);
        flight.getAirplane().setBusy(false);
        airplaneRepository.save(flight.getAirplane());
        modelMapper.map(entity, flight);
        flight.getAirplane().setBusy(true);
        return mapper.toDTO(repository.save(flight));
    }

    @Override
    public void delete(Long id) throws NoSuchFlightException {
        Flight flight = repository.findById(id).orElseThrow(NoSuchFlightException::new);
        flight.setDeleted(true);
        repository.save(flight);
    }
}
