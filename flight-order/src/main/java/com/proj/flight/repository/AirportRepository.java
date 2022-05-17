package com.proj.flight.repository;

import com.proj.flight.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByIcaoCode(String icaoCode);
}
