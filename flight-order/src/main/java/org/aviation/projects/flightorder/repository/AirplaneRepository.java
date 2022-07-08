package org.aviation.projects.flightorder.repository;

import io.micrometer.core.annotation.Timed;
import org.aviation.projects.flightorder.entity.Airplane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    Optional<Airplane> findByIcaoCodeAndDeletedFalse(String icaoCode);
    @Timed
    Page<Airplane> findAllByDeletedFalse(Pageable pageable);

    List<Airplane> findAllByDeletedFalseAndBusyFalse();
}
