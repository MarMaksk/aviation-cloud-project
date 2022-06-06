package com.proj.demo.repository;

import com.proj.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameOrderByExpirationDate(String name);

    Optional<Product> findByCode(int code);

    List<Product> findAllByTagsOrderByName(String tag);
}
