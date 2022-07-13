package org.aviation.projects.flightorder.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.service.CRUD;
import org.aviation.projects.flightorder.dto.ExaminationDTO;
import org.aviation.projects.flightorder.repository.ExaminationRepository;
import org.aviation.projects.flightorder.service.mapper.ExaminationDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExaminationService implements CRUD<ExaminationDTO> {

    static Logger LOG = LoggerFactory.getLogger(ExaminationService.class);
    ExaminationRepository examinationRepository;
    ExaminationDTOMapper mapper;

    public Page<ExaminationDTO> findAll(Pageable pageable) {
        LOG.info("Finding all examinations");
        return examinationRepository.findAllByDeletedFalse(pageable).map(mapper::toDTO);
    }

    @Override
    public void create(ExaminationDTO dto) throws Exception {
        LOG.info("Creating examination");
        examinationRepository.save(mapper.toEntity(dto));
    }

    @Override
    public ExaminationDTO findByCode(String iataCode) throws Exception {
        LOG.info("Finding examination by code");
        return mapper.toDTO(examinationRepository.findAllByAirplane_IcaoCodeAndDeletedFalseOrderByDate(iataCode));
    }

    @Override
    public ExaminationDTO update(ExaminationDTO dto) throws Exception {
        LOG.info("Updating examination");
        return null;
    }

    @Override
    @Deprecated
    public void delete(String deprecated) throws Exception {
        LOG.info("Deleting examination");
//        Examination examination = examinationRepository.findById(id).orElseThrow(NoSuchExaminationException::new);
//        examination.setDeleted(true);
//        examinationRepository.save(examination);
    }
}
