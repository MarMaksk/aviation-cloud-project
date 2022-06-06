package com.proj.flight.repository;

import com.proj.flight.entity.Airplane;
import com.proj.flight.entity.Airport;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    Optional<Airplane> findByIataCode(String iata);

    List<Airplane> findAllByDeletedFalse();

    List<Airplane> findAllByDeletedFalseAndBusyFalse();
}
