package com.proj.flight.service.mapper;

import com.proj.flight.dto.AirplaneDTO;
import com.proj.flight.dto.AirportDTO;
import com.proj.flight.entity.Airplane;
import lombok.Data;
import org.aviation.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneDTOMapper implements EntityToDTOMapper<Airplane, AirplaneDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Airplane toEntity(AirplaneDTO dto) {
        return mapper.map(dto, Airplane.class);
    }

    @Override
    public AirplaneDTO toDTO(Airplane entity) {
        return mapper.map(entity, AirplaneDTO.class);
    }
}
