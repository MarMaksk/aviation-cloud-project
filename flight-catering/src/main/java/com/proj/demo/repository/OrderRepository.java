package com.proj.demo.repository;


import com.proj.demo.entity.Order;
import com.proj.demo.exception.NoSuchOrderException;
import org.aviation.entity.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByIataCode(String iataCode);

    Optional<Order> findByIcaoCode(String icaoCode);

    Optional<Order> findByProductOrderId(Integer productOrderId);
}
