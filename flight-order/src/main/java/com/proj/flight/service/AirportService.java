package com.proj.flight.service;

import com.proj.flight.dto.AirportDTO;
import com.proj.flight.entity.Airport;
import com.proj.flight.exception.NoSuchAirportException;
import com.proj.flight.repository.AirportRepository;
import com.proj.flight.service.mapper.AirportDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.service.CRUD;
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
