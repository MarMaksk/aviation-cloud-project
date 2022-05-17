package com.proj.flight.repository;

import com.proj.flight.entity.Seat;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllByAirplane_Id(Long sort);
}
