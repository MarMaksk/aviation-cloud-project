package org.aviation.projects.flightorder.service.mapper;

import org.aviation.projects.flightorder.dto.AirportDTO;
import org.aviation.projects.flightorder.entity.Airport;
import lombok.RequiredArgsConstructor;
import org.aviation.projects.commons.service.mapper.EntityToDTOMapper;
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
