package com.proj.flight.service.mapper;

import com.proj.flight.dto.FlightDTO;
import com.proj.flight.entity.Airplane;
import com.proj.flight.entity.Airport;
import com.proj.flight.entity.Flight;
import com.proj.flight.entity.enums.FlightStatus;
import com.proj.flight.exception.NoSuchAirplaneException;
import com.proj.flight.exception.NoSuchAirportException;
import com.proj.flight.exception.NoSuchFlightException;
import com.proj.flight.repository.AirplaneRepository;
import com.proj.flight.repository.AirportRepository;
import com.proj.flight.repository.FlightRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class FlightDTOMapper implements EntityToDTOMapper<Flight, FlightDTO> {

    ModelMapper mapper;
    AirportRepository airportRepository;
    AirplaneRepository airplaneRepository;
    FlightRepository flightRepository;

    @Override
    public Flight toEntity(FlightDTO dto) throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        Flight flight = mapper.map(dto, Flight.class);
        Airplane airplane = airplaneRepository.findByIataCodeAndDeletedFalse(dto.getIataCode())
                .orElseThrow(NoSuchAirplaneException::new);
        Airport departure = airportRepository.findByIcaoCodeAndDeletedFalse(dto.getIcaoCodeDeparture())
                .orElseThrow(NoSuchAirportException::new);
        Airport arrival = airportRepository.findByIcaoCodeAndDeletedFalse(dto.getIcaoCodeArrival())
                .orElseThrow(NoSuchAirportException::new);
        flight.setAirplane(airplane);
        flight.setDepartureAirport(departure);
        flight.setArrivalAirport(arrival);
        if (dto.getStatus() != null)
            flight.setStatus(FlightStatus.fromString(dto.getStatus()));
        if (dto.getFlightNumberAltFlight() != null)
            flight.setAlternativeFlights(flightRepository.findByFlightNumber(dto.getFlightNumberAltFlight())
                    .orElseThrow(NoSuchFlightException::new));
        return flight;
    }

    @Override
    public FlightDTO toDTO(Flight entity) {
        FlightDTO dto = mapper.map(entity, FlightDTO.class);
        dto.setIataCode(entity.getAirplane().getIataCode());
        dto.setIcaoCodeArrival(entity.getArrivalAirport().getIcaoCode());
        dto.setIcaoCodeDeparture(entity.getDepartureAirport().getIcaoCode());
        dto.setStatus(entity.getStatus().getName());
        if (entity.getAlternativeFlights() != null)
            dto.setFlightNumberAltFlight(entity.getAlternativeFlights().getFlightNumber());
        return dto;
    }
}
