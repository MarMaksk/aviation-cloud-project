package org.aviation.projects.flightorder.service.mapper;

import lombok.RequiredArgsConstructor;
import org.aviation.projects.commons.service.mapper.EntityToDTOMapper;
import org.aviation.projects.flightorder.dto.ExaminationDTO;
import org.aviation.projects.flightorder.entity.Airplane;
import org.aviation.projects.flightorder.entity.Examination;
import org.aviation.projects.flightorder.exception.NoSuchAirplaneException;
import org.aviation.projects.flightorder.repository.AirplaneRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExaminationDTOMapper implements EntityToDTOMapper<Examination, ExaminationDTO> {

    static Logger LOG = LoggerFactory.getLogger(ExaminationDTOMapper.class);
    private final ModelMapper mapper;
    private final AirplaneRepository repository;

    @Override
    public Examination toEntity(ExaminationDTO dto) throws NoSuchAirplaneException {
        LOG.info("Mapping ExaminationDTO to Examination");
        Examination examination = mapper.map(dto, Examination.class);
        Airplane airplane = repository.findByIcaoCodeAndDeletedFalse(dto.getIcaoCode()).orElseThrow(NoSuchAirplaneException::new);
        examination.setAirplane(airplane);
        return examination;
    }

    @Override
    public ExaminationDTO toDTO(Examination entity) {
        LOG.info("Mapping Examination to ExaminationDTO");
        ExaminationDTO dto = mapper.map(entity, ExaminationDTO.class);
        dto.setIcaoCode(entity.getAirplane().getIcaoCode());
        return dto;
    }
}
