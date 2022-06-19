package com.proj.flight.service.mapper;

import com.proj.flight.dto.AirplaneDTO;
import com.proj.flight.entity.Airplane;
import lombok.RequiredArgsConstructor;
import org.aviation.service.mapper.EntityToDTOMapper;
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
