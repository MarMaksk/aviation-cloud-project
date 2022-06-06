package com.proj.flight.service.mapper;

import com.proj.flight.dto.ExaminationDTO;
import com.proj.flight.entity.Airplane;
import com.proj.flight.entity.Examination;
import com.proj.flight.exception.NoSuchAirplaneException;
import com.proj.flight.repository.AirplaneRepository;
import lombok.AllArgsConstructor;
import org.aviation.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExaminationDTOMapper implements EntityToDTOMapper<Examination, ExaminationDTO> {

    private final ModelMapper mapper;
    private final AirplaneRepository repository;

    @Override
    public Examination toEntity(ExaminationDTO dto) throws NoSuchAirplaneException {
        Examination examination = mapper.map(dto, Examination.class);
        Airplane airplane = repository.findByIataCode(dto.getIataCode()).orElseThrow(NoSuchAirplaneException::new);
        examination.setAirplane(airplane);
        return examination;
    }

    @Override
    public ExaminationDTO toDTO(Examination entity) {
        ExaminationDTO dto = mapper.map(entity, ExaminationDTO.class);
        dto.setIataCode(entity.getAirplane().getIataCode());
        return dto;
    }
}
