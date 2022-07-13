package org.aviation.projects.flightorder.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.service.CRUD;
import org.aviation.projects.flightorder.dto.AirportDTO;
import org.aviation.projects.flightorder.entity.Airport;
import org.aviation.projects.flightorder.exception.NoSuchAirportException;
import org.aviation.projects.flightorder.repository.AirportRepository;
import org.aviation.projects.flightorder.service.mapper.AirportDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirportService implements CRUD<AirportDTO> {

    static Logger LOG = LoggerFactory.getLogger(AirportService.class);
    AirportRepository airportRepository;
    AirportDTOMapper airportDTOMapper;


    public Page<AirportDTO> findAll(Pageable pageable) {
        LOG.info("Finding all airports");
        return airportRepository.findAllByDeletedFalse(pageable).map(airportDTOMapper::toDTO);
    }

    @Override
    public void create(AirportDTO dto) {
        LOG.info("Creating airport");
        airportRepository.save(airportDTOMapper.toEntity(dto));
    }

    @Override
    public AirportDTO findByCode(String code) throws NoSuchAirportException {
        LOG.info("Finding airport by code");
        Airport airport = airportRepository.findByIataCodeAndDeletedFalse(code).orElseThrow(NoSuchAirportException::new);
        return airportDTOMapper.toDTO(airport);
    }


    @Override
    public AirportDTO update(AirportDTO dto) throws Exception {
        Airport airport = airportDTOMapper.toEntity(dto);
        return airportDTOMapper.toDTO(airportRepository.save(airport));
    }

    @Override
    public void delete(String icaoCode) throws Exception {
        LOG.info("Deleting airport");
        Airport airport = airportRepository.findByIataCodeAndDeletedFalse(icaoCode).orElseThrow(NoSuchAirportException::new);
        airport.setDeleted(true);
        airportRepository.save(airport);
    }
}
