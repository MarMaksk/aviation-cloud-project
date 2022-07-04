package org.aviation.projects.flightorder.repository;

import io.micrometer.core.annotation.Timed;
import org.aviation.projects.flightorder.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByDeletedFalse();

    Optional<Flight> findByFlightNumber(String number);

    @Timed("findAltFlights")
    @Query("FROM Flight fl " +
            "join fl.airplane airplane " +
            "join fl.departureAirport airportDep " +
            "join fl.arrivalAirport airportArr " +
            "where airportDep.icaoCode = :icaoCodeDep " +
            "and airportArr.icaoCode =:icaoCodeArr " +
            "and airplane.loadCapacity >= :capacity " +
            "and fl.departure >= :departure " +
            "and fl.flightNumber <> :flightNumber")
    List<Flight> findAllAlternativeFlights(@Param("icaoCodeDep") String icaoCodeDep,
                                           @Param("icaoCodeArr") String icaoCodeArr,
                                           @Param("capacity") Integer capacity,
                                           @Param("departure") LocalDateTime departure,
                                           @Param("flightNumber") String flightNumber);
}
