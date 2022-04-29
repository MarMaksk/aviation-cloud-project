package com.proj.demo.repository;


import com.proj.demo.entity.Order;
import com.proj.demo.exception.NoSuchOrderException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByIataCode(String iataCode) throws NoSuchOrderException;

    Optional<Order> findByIcaoCode(String icaoCode) throws NoSuchOrderException;
}
