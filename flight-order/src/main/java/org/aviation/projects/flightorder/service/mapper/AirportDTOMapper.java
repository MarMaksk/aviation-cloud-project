package org.aviation.projects.flightorder.service.mapper;

import lombok.RequiredArgsConstructor;
import org.aviation.projects.commons.service.mapper.EntityToDTOMapper;
import org.aviation.projects.flightorder.dto.AirportDTO;
import org.aviation.projects.flightorder.entity.Airport;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportDTOMapper implements EntityToDTOMapper<Airport, AirportDTO> {

    static Logger LOG = LoggerFactory.getLogger(AirportDTOMapper.class);
    private final ModelMapper mapper;

    @Override
    public Airport toEntity(AirportDTO dto) {
        LOG.info("Mapping AirportDTO to Airport");
        return mapper.map(dto, Airport.class);
    }

    @Override
    public AirportDTO toDTO(Airport entity) {
        LOG.info("Mapping Airport to AirportDTO");
        return mapper.map(entity, AirportDTO.class);
    }
}
