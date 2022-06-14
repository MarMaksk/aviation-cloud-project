package com.proj.flight.repository;

import com.proj.flight.entity.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByIcaoCodeAndDeletedFalse(String icaoCode);

    Page<Airport> findAllByDeletedFalse(Pageable pageable);
}
