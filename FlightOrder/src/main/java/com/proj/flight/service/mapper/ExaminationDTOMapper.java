package com.proj.flight.service.mapper;

import com.proj.flight.dto.AirplaneDTO;
import com.proj.flight.dto.ExaminationDTO;
import com.proj.flight.entity.AbstractEntity;
import com.proj.flight.entity.Airplane;
import com.proj.flight.entity.Examination;
import com.proj.flight.exception.NoSuchAirplaneException;
import com.proj.flight.repository.AirplaneRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExaminationDTOMapper implements EntityToDTOMapper<Examination, ExaminationDTO> {

    private final ModelMapper mapper;
    private final AirplaneRepository repository;

    @Override
    public Examination toEntity(ExaminationDTO dto) {
        Examination examination = mapper.map(dto, Examination.class);
        Airplane airplane = repository.findByIataCode(dto.getIataCode()).orElseThrow(NoSuchAirplaneException::new);
        examination.setAirplane(airplane);
        return examination;
    }

    @Override
    public ExaminationDTO toDTO(Examination entity) {
        mapper.createTypeMap(Examination.class, ExaminationDTO.class)
                .addMapping(ex -> ex.getAirplane().getIataCode(), ExaminationDTO::setIataCode);
        return mapper.map(entity, ExaminationDTO.class);
    }
}
