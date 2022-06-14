package com.proj.flight.service;

import com.proj.flight.dto.ExaminationDTO;
import com.proj.flight.entity.Examination;
import com.proj.flight.exception.NoSuchExaminationException;
import com.proj.flight.repository.ExaminationRepository;
import com.proj.flight.service.mapper.ExaminationDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.service.CRUD;
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
