package org.aviation.projects.flightorder.service;

import org.aviation.projects.flightorder.dto.ExaminationDTO;
import org.aviation.projects.flightorder.repository.ExaminationRepository;
import org.aviation.projects.flightorder.service.mapper.ExaminationDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.service.CRUD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExaminationService implements CRUD<ExaminationDTO> {

    ExaminationRepository examinationRepository;
    ExaminationDTOMapper mapper;

    public Page<ExaminationDTO> findAll(Pageable pageable) {
        return examinationRepository.findAllByDeletedFalse(pageable).map(mapper::toDTO);
    }

    @Override
    public void create(ExaminationDTO dto) throws Exception {
        examinationRepository.save(mapper.toEntity(dto));
    }

    @Override
    public ExaminationDTO findByCode(String iataCode) throws Exception {
        return mapper.toDTO(examinationRepository.findAllByAirplane_IataCodeAndDeletedFalseOrderByDate(iataCode));
    }

    @Override
    public ExaminationDTO update(ExaminationDTO dto) throws Exception {
        return null;
    }

    @Override
    @Deprecated
    public void delete(String deprecated) throws Exception {
//        Examination examination = examinationRepository.findById(id).orElseThrow(NoSuchExaminationException::new);
//        examination.setDeleted(true);
//        examinationRepository.save(examination);
    }
}
