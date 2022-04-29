package com.proj.flight.repository;

import com.proj.flight.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

}
