package com.proj.flight.service.mapper;

import com.proj.flight.dto.FlightDTO;
import com.proj.flight.entity.Airplane;
import com.proj.flight.entity.Airport;
import com.proj.flight.entity.Flight;
import com.proj.flight.entity.enums.FlightStatus;
import com.proj.flight.exception.NoSuchAirplaneException;
import com.proj.flight.exception.NoSuchAirportException;
import com.proj.flight.repository.AirplaneRepository;
import com.proj.flight.repository.AirportRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FlightDTOMapper implements EntityToDTOMapper<Flight, FlightDTO> {

    ModelMapper mapper;
    AirportRepository airportRepository;
    AirplaneRepository airplaneRepository;

    @Override
    public Flight toEntity(FlightDTO dto) throws NoSuchAirplaneException, NoSuchAirportException {
        Flight flight = mapper.map(dto, Flight.class);
        Airplane airplane = airplaneRepository.findByIataCode(dto.getIataCode())
                .orElseThrow(NoSuchAirplaneException::new);
        Airport departure = airportRepository.findByIcaoCode(dto.getIcaoCodeDeparture())
                .orElseThrow(NoSuchAirportException::new);
        Airport arrival = airportRepository.findByIcaoCode(dto.getIcaoCodeArrival())
                .orElseThrow(NoSuchAirportException::new);
        flight.setAirplane(airplane);
        flight.setDepartureAirport(departure);
        flight.setArrivalAirport(arrival);
        return flight;
    }

    @Override
    public FlightDTO toDTO(Flight entity) {
        FlightDTO dto = mapper.map(entity, FlightDTO.class);
        dto.setIataCode(entity.getAirplane().getIataCode());
        dto.setIcaoCodeArrival(entity.getArrivalAirport().getIcaoCode());
        dto.setIcaoCodeDeparture(entity.getDepartureAirport().getIcaoCode());
        dto.setStatus(entity.getStatus().getName());
        return dto;
    }
}
