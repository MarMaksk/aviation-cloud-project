package com.proj.flight.repository;

import com.proj.flight.entity.Airplane;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    Optional<Airplane> findByIataCode(String iata);

}
