package org.aviation.projects.flightorder.repository;

import org.aviation.projects.flightorder.entity.Examination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    List<Examination> findAllByAirplane_IataCodeAndDeletedFalse(String iataCode);

    Examination findAllByAirplane_IataCodeAndDeletedFalseOrderByDate(String iataCode);

    Page<Examination> findAllByDeletedFalse(Pageable pageable);
}