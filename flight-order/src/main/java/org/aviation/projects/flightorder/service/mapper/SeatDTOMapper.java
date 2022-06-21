package org.aviation.projects.flightorder.service.mapper;

import org.aviation.projects.flightorder.dto.SeatDTO;
import org.aviation.projects.flightorder.entity.Airplane;
import org.aviation.projects.flightorder.entity.Seat;
import org.aviation.projects.flightorder.exception.NoSuchAirplaneException;
import org.aviation.projects.flightorder.repository.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import org.aviation.projects.commons.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatDTOMapper implements EntityToDTOMapper<Seat, SeatDTO> {

    private final ModelMapper mapper;
    private final AirplaneRepository repository;

    @Override
    public Seat toEntity(SeatDTO dto) throws NoSuchAirplaneException {
        Seat seat = mapper.map(dto, Seat.class);
        Airplane airplane = repository.findByIataCodeAndDeletedFalse(dto.getIataCode())
                .orElseThrow(NoSuchAirplaneException::new);
        seat.setAirplane(airplane);
        return seat;
    }

    @Override
    public SeatDTO toDTO(Seat entity) {
        mapper.createTypeMap(Seat.class, SeatDTO.class)
                .addMapping(en -> en.getAirplane().getIataCode(), SeatDTO::setIataCode);
        return mapper.map(entity, SeatDTO.class);
    }
}
