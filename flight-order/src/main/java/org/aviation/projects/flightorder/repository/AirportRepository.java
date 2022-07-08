package org.aviation.projects.flightorder.repository;

import io.micrometer.core.annotation.Timed;
import org.aviation.projects.flightorder.entity.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByIataCodeAndDeletedFalse(String iataCode);

    @Timed("findAllAirports")
    Page<Airport> findAllByDeletedFalse(Pageable pageable);
}
