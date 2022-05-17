package com.proj.flight.service;

import com.proj.flight.dto.FlightDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FlightService implements CRUD<FlightDTO> {

    ModelMapper mapper;
    KafkaTemplate<String, String> template;

    @Override
    public void create(FlightDTO entity) {
        template.send("topic1", "IATA: ", entity.getIataCode());
        ListenableFuture<SendResult<String, String>> send = template.send("topic1", "DEPARTURE: ", entity.getDeparture().toString());
    }

    @Override
    public FlightDTO findById(Long id) {
        return null;
    }

    @Override
    public FlightDTO update(FlightDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
