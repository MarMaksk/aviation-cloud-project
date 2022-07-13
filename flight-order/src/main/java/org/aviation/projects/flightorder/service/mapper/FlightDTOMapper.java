package org.aviation.projects.flightorder.service.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.service.mapper.EntityToDTOMapper;
import org.aviation.projects.flightorder.dto.FlightDTO;
import org.aviation.projects.flightorder.entity.Airplane;
import org.aviation.projects.flightorder.entity.Airport;
import org.aviation.projects.flightorder.entity.Flight;
import org.aviation.projects.flightorder.entity.enums.FlightStatus;
import org.aviation.projects.flightorder.exception.NoSuchAirplaneException;
import org.aviation.projects.flightorder.exception.NoSuchAirportException;
import org.aviation.projects.flightorder.exception.NoSuchFlightException;
import org.aviation.projects.flightorder.repository.AirplaneRepository;
import org.aviation.projects.flightorder.repository.AirportRepository;
import org.aviation.projects.flightorder.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class FlightDTOMapper implements EntityToDTOMapper<Flight, FlightDTO> {

    static Logger LOG = LoggerFactory.getLogger(FlightDTOMapper.class);
    ModelMapper mapper;
    AirportRepository airportRepository;
    AirplaneRepository airplaneRepository;
    FlightRepository flightRepository;

    @Override
    public Flight toEntity(FlightDTO dto) throws NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        LOG.info("Mapping FlightDTO to Flight");
        Flight flight = mapper.map(dto, Flight.class);
        Airplane airplane = airplaneRepository.findByIcaoCodeAndDeletedFalse(dto.getIcaoCode())
                .orElseThrow(NoSuchAirplaneException::new);
        Airport departure = airportRepository.findByIataCodeAndDeletedFalse(dto.getIataCodeDeparture())
                .orElseThrow(NoSuchAirportException::new);
        Airport arrival = airportRepository.findByIataCodeAndDeletedFalse(dto.getIataCodeArrival())
                .orElseThrow(NoSuchAirportException::new);
        flight.setAirplane(airplane);
        flight.setDepartureAirport(departure);
        flight.setArrivalAirport(arrival);
        if (dto.getStatus() != null)
            flight.setStatus(FlightStatus.fromString(dto.getStatus()));
        if (dto.getFlightNumberAltFlight() != null)
            flight.setAlternativeFlights(flightRepository.findByFlightNumber(dto.getFlightNumberAltFlight())
                    .orElseThrow(NoSuchFlightException::new));
        LOG.info("Mapped FlightDTO to Flight");
        return flight;
    }

    @Override
    public FlightDTO toDTO(Flight entity) {
        LOG.info("Mapping Flight to FlightDTO");
        FlightDTO dto = mapper.map(entity, FlightDTO.class);
        dto.setIcaoCode(entity.getAirplane().getIcaoCode());
        dto.setIataCodeArrival(entity.getArrivalAirport().getIataCode());
        dto.setIataCodeDeparture(entity.getDepartureAirport().getIataCode());
        dto.setStatus(entity.getStatus().getName());
        if (entity.getAlternativeFlights() != null)
            dto.setFlightNumberAltFlight(entity.getAlternativeFlights().getFlightNumber());
        LOG.info("Mapped Flight to FlightDTO");
        return dto;
    }
}
