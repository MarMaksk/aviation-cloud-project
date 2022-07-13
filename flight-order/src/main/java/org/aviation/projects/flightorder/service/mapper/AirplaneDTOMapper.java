package org.aviation.projects.flightorder.service.mapper;

import lombok.RequiredArgsConstructor;
import org.aviation.projects.commons.service.mapper.EntityToDTOMapper;
import org.aviation.projects.flightorder.dto.AirplaneDTO;
import org.aviation.projects.flightorder.entity.Airplane;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirplaneDTOMapper implements EntityToDTOMapper<Airplane, AirplaneDTO> {

    static Logger LOG = LoggerFactory.getLogger(AirplaneDTOMapper.class);
    private final ModelMapper mapper;

    @Override
    public Airplane toEntity(AirplaneDTO dto) {
        LOG.info("Mapping AirplaneDTO to Airplane");
        return mapper.map(dto, Airplane.class);
    }

    @Override
    public AirplaneDTO toDTO(Airplane entity) {
        LOG.info("Mapping Airplane to AirplaneDTO");
        return mapper.map(entity, AirplaneDTO.class);
    }
}
