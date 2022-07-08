package org.aviation.projects.flightorder.service;

import org.aviation.projects.flightorder.dto.AirplaneDTO;
import org.aviation.projects.flightorder.entity.Airplane;
import org.aviation.projects.flightorder.exception.NoSuchAirplaneException;
import org.aviation.projects.flightorder.repository.AirplaneRepository;
import org.aviation.projects.flightorder.service.mapper.AirplaneDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.service.CRUD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirplaneService implements CRUD<AirplaneDTO> {

    AirplaneRepository repository;
    AirplaneDTOMapper mapper;


    public Page<AirplaneDTO> findAll(Pageable pageable) {
        return repository.findAllByDeletedFalse(pageable).map(mapper::toDTO);
    }

    public List<AirplaneDTO> findAllNoBusy() {
        return mapper.toDTOs(repository.findAllByDeletedFalseAndBusyFalse());
    }

    @Override
    public void create(AirplaneDTO dto) {
        repository.save(mapper.toEntity(dto));
    }

    @Override
    public AirplaneDTO findByCode(String code) throws Exception {
        return mapper.toDTO(repository.findByIcaoCodeAndDeletedFalse(code).orElseThrow(NoSuchAirplaneException::new));
    }


    @Override
    public AirplaneDTO update(AirplaneDTO dto) {
        Airplane save = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(save);
    }

    @Override
    public void delete(String iataCode) throws NoSuchAirplaneException {
        Airplane airplane = repository.findByIcaoCodeAndDeletedFalse(iataCode).orElseThrow(NoSuchAirplaneException::new);
        airplane.setDeleted(true);
        repository.save(airplane);
    }
}
