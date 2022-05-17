package com.proj.flight.service.mapper;

import com.proj.flight.dto.SeatDTO;
import com.proj.flight.entity.Airplane;
import com.proj.flight.entity.Seat;
import com.proj.flight.exception.NoSuchAirplaneException;
import com.proj.flight.repository.AirplaneRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeatDTOMapper implements EntityToDTOMapper<Seat, SeatDTO> {

    private final ModelMapper mapper;
    private final AirplaneRepository repository;

    @Override
    public Seat toEntity(SeatDTO dto) {
        Seat seat = mapper.map(dto, Seat.class);
        Airplane airplane = repository.findByIataCode(dto.getIataCode())
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
