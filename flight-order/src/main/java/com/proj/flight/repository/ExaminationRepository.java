package com.proj.flight.repository;

import com.proj.flight.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    List<Examination> findAllByAirplane_IataCodeAndDeletedFalse(String iataCode);

    Examination findAllByAirplane_IataCodeAndDeletedFalseOrderByDate(String iataCode);

    List<Examination> findAllByDeletedFalse();
}
