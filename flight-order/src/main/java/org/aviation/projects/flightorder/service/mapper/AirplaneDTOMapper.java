package org.aviation.projects.flightorder.service.mapper;

import org.aviation.projects.flightorder.dto.AirplaneDTO;
import org.aviation.projects.flightorder.entity.Airplane;
import lombok.RequiredArgsConstructor;
import org.aviation.projects.commons.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirplaneDTOMapper implements EntityToDTOMapper<Airplane, AirplaneDTO> {

    private final ModelMapper mapper;

    @Override
    public Airplane toEntity(AirplaneDTO dto) {
        return mapper.map(dto, Airplane.class);
    }

    @Override
    public AirplaneDTO toDTO(Airplane entity) {
        return mapper.map(entity, AirplaneDTO.class);
    }
}
