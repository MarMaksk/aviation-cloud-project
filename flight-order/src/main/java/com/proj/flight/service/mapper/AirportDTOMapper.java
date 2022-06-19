package com.proj.flight.service.mapper;

import com.proj.flight.dto.AirportDTO;
import com.proj.flight.entity.Airport;
import lombok.RequiredArgsConstructor;
import org.aviation.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportDTOMapper implements EntityToDTOMapper<Airport, AirportDTO> {

    private final ModelMapper mapper;

    @Override
    public Airport toEntity(AirportDTO dto) {
        return mapper.map(dto, Airport.class);
    }

    @Override
    public AirportDTO toDTO(Airport entity) {
        return mapper.map(entity, AirportDTO.class);
    }
}
