package com.proj.demo.repository;


import com.proj.demo.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByDeletedFalse(Pageable pageable);

    Optional<Order> findByIataCode(String iataCode);

    Optional<Order> findByIcaoCode(String icaoCode);

    Optional<Order> findByProductOrderIdAndDeletedFalse(Integer productOrderId);

}
