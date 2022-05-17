package com.proj.flight.service.mapper;

import com.proj.flight.dto.AirportDTO;
import com.proj.flight.entity.Airport;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportDTOMapper implements EntityToDTOMapper<Airport, AirportDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Airport toEntity(AirportDTO dto) {
        return mapper.map(dto, Airport.class);
    }

    @Override
    public AirportDTO toDTO(Airport entity) {
        return mapper.map(entity, AirportDTO.class);
    }
}
