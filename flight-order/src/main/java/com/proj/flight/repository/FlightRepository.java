package com.proj.flight.repository;

import com.proj.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByDeletedFalse();

    Optional<Flight> findByFlightNumber(String number);
}
