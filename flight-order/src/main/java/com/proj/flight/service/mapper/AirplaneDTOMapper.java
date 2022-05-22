package com.proj.flight.service.mapper;

import com.proj.flight.dto.AirportDTO;
import com.proj.flight.entity.Airplane;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneDTOMapper implements EntityToDTOMapper<Airplane, AirportDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Airplane toEntity(AirportDTO dto) {
        return mapper.map(dto, Airplane.class);
    }

    @Override
    public AirportDTO toDTO(Airplane entity) {
        return mapper.map(entity, AirportDTO.class);
    }
}
