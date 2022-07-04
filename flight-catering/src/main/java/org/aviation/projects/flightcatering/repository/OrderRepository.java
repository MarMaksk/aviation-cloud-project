package org.aviation.projects.flightcatering.repository;


import io.micrometer.core.annotation.Timed;
import org.aviation.projects.flightcatering.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Timed("findAllOrderWithPageable")
    Page<Order> findAllByDeletedFalse(Pageable pageable);

    Optional<Order> findByIataCode(String iataCode);

    Optional<Order> findByIcaoCode(String icaoCode);
    @Timed("findOrder")
    Optional<Order> findByProductOrderIdAndDeletedFalse(Integer productOrderId);

}
