package com.proj.flight.service;

import com.proj.flight.dto.AirplaneDTO;
import com.proj.flight.entity.Airplane;
import com.proj.flight.exception.NoSuchAirplaneException;
import com.proj.flight.repository.AirplaneRepository;
import com.proj.flight.service.mapper.AirplaneDTOMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.service.CRUD;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirplaneService implements CRUD<AirplaneDTO> {

    AirplaneRepository repository;
    AirplaneDTOMapper mapper;


    public List<AirplaneDTO> findAll() {
        return mapper.toDTOs(repository.findAllByDeletedFalse());
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
        return mapper.toDTO(repository.findByIataCode(code).orElseThrow(NoSuchAirplaneException::new));
    }


    @Override
    public AirplaneDTO update(AirplaneDTO dto) {
        Airplane save = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(save);
    }

    @Override
    public void delete(Long id) throws NoSuchAirplaneException {
        Airplane airplane = repository.findById(id).orElseThrow(NoSuchAirplaneException::new);
        airplane.setDeleted(true);
        repository.save(airplane);
    }
}
