package org.aviation.projects.flightorder.service;

import org.aviation.projects.flightorder.dto.AirportDTO;
import org.aviation.projects.flightorder.entity.Airport;
import org.aviation.projects.flightorder.exception.NoSuchAirportException;
import org.aviation.projects.flightorder.repository.AirportRepository;
import org.aviation.projects.flightorder.service.mapper.AirportDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.service.CRUD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirportService implements CRUD<AirportDTO> {

    AirportRepository airportRepository;
    AirportDTOMapper airportDTOMapper;


    public Page<AirportDTO> findAll(Pageable pageable) {
        return airportRepository.findAllByDeletedFalse(pageable).map(airportDTOMapper::toDTO);
    }

    @Override
    public void create(AirportDTO dto) {
        airportRepository.save(airportDTOMapper.toEntity(dto));
    }

    @Override
    public AirportDTO findByCode(String code) throws NoSuchAirportException {
        Airport airport = airportRepository.findByIcaoCodeAndDeletedFalse(code).orElseThrow(NoSuchAirportException::new);
        return airportDTOMapper.toDTO(airport);
    }


    @Override
    public AirportDTO update(AirportDTO dto) throws Exception {
        Airport airport = airportDTOMapper.toEntity(dto);
        return airportDTOMapper.toDTO(airportRepository.save(airport));
    }

    @Override
    public void delete(String icaoCode) throws Exception {
        Airport airport = airportRepository.findByIcaoCodeAndDeletedFalse(icaoCode).orElseThrow(NoSuchAirportException::new);
        airport.setDeleted(true);
        airportRepository.save(airport);
    }
}
