package com.proj.flight.service;

import com.proj.flight.dto.AirplaneDTO;
import com.proj.flight.entity.Airplane;
import com.proj.flight.repository.AirplaneRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirplaneService implements CRUD<AirplaneDTO> {

    AirplaneRepository repository;
    ModelMapper mapper;
    KafkaTemplate<String, String> template;

    @Override
    public void create(AirplaneDTO entity) {
        repository.save(mapper.map(entity, Airplane.class));
    }

    @Override
    public AirplaneDTO findById(Long id) {
        return null;
    }

    @Override
    public AirplaneDTO update(AirplaneDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
