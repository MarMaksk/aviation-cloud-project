package org.aviation.projects.flightorder.service.mapper;

import lombok.RequiredArgsConstructor;
import org.aviation.projects.commons.service.mapper.EntityToDTOMapper;
import org.aviation.projects.flightorder.dto.SeatDTO;
import org.aviation.projects.flightorder.entity.Airplane;
import org.aviation.projects.flightorder.entity.Seat;
import org.aviation.projects.flightorder.exception.NoSuchAirplaneException;
import org.aviation.projects.flightorder.repository.AirplaneRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatDTOMapper implements EntityToDTOMapper<Seat, SeatDTO> {

    static Logger LOG = LoggerFactory.getLogger(SeatDTOMapper.class);
    private final ModelMapper mapper;
    private final AirplaneRepository repository;

    @Override
    public Seat toEntity(SeatDTO dto) throws NoSuchAirplaneException {
        LOG.info("Mapping SeatDTO to Seat");
        Seat seat = mapper.map(dto, Seat.class);
        Airplane airplane = repository.findByIcaoCodeAndDeletedFalse(dto.getIataCode())
                .orElseThrow(NoSuchAirplaneException::new);
        seat.setAirplane(airplane);
        return seat;
    }

    @Override
    public SeatDTO toDTO(Seat entity) {
        LOG.info("Mapping Seat to SeatDTO");
        mapper.createTypeMap(Seat.class, SeatDTO.class)
                .addMapping(en -> en.getAirplane().getIcaoCode(), SeatDTO::setIataCode);
        return mapper.map(entity, SeatDTO.class);
    }
}
