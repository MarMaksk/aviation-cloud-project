package com.proj.flight.service;

import com.proj.flight.dto.AirportDTO;
import com.proj.flight.entity.Airport;
import com.proj.flight.exception.NoSuchAirportException;
import com.proj.flight.repository.AirportRepository;
import com.proj.flight.service.mapper.AirportDTOMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.service.CRUD;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirportService implements CRUD<AirportDTO> {

    AirportRepository airportRepository;
    AirportDTOMapper airportDTOMapper;


    public List<AirportDTO> findAll() {
        return airportDTOMapper.toDTOs(airportRepository.findAllByDeletedFalse());
    }

    @Override
    public void create(AirportDTO dto) {
        airportRepository.save(airportDTOMapper.toEntity(dto));
    }

    @Override
    public AirportDTO findByCode(String code) throws NoSuchAirportException {
        Airport airport = airportRepository.findByIcaoCode(code).orElseThrow(NoSuchAirportException::new);
        return airportDTOMapper.toDTO(airport);
    }


    @Override
    public AirportDTO update(AirportDTO dto) throws Exception {
        Airport airport = airportDTOMapper.toEntity(dto);
        return airportDTOMapper.toDTO(airportRepository.save(airport));
    }

    @Override
    public void delete(Long id) throws Exception {

    }
}
