package com.proj.flight.service;

import com.proj.flight.dto.ExaminationDTO;
import com.proj.flight.entity.Examination;
import com.proj.flight.exception.NoSuchExaminationException;
import com.proj.flight.repository.ExaminationRepository;
import com.proj.flight.service.mapper.ExaminationDTOMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.service.CRUD;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExaminationService implements CRUD<ExaminationDTO> {

    ExaminationRepository examinationRepository;
    ExaminationDTOMapper mapper;

    public List<ExaminationDTO> findAll() {
        return mapper.toDTOs(examinationRepository.findAllByDeletedFalse());
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
    public void delete(Long id) throws Exception {
        Examination examination = examinationRepository.findById(id).orElseThrow(NoSuchExaminationException::new);
        examination.setDeleted(true);
        examinationRepository.save(examination);
    }
}
