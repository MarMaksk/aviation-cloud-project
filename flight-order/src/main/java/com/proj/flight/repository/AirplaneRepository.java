package com.proj.flight.repository;

import com.proj.flight.entity.Airplane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    Optional<Airplane> findByIataCode(String iata);

    Page<Airplane> findAllByDeletedFalse(Pageable pageable);

    List<Airplane> findAllByDeletedFalseAndBusyFalse();
}
