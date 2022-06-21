package org.aviation.projects.flightorder.repository;

import org.aviation.projects.flightorder.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllByAirplane_Id(Long sort);
}
