package com.proj.demo.repository;

import com.proj.demo.entity.Order;
import com.proj.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByDeletedFalse(Pageable pageable);

    List<Product> findAllByNameOrderByExpirationDate(String name);

    Optional<Product> findByCodeAndDeletedFalse(int code);

    @Query("FROM Product pr " +
            "join pr.tags t " +
            "where t.name = :tag")
    List<Product> findAllByTagsOrderByName(@Param("tag") String tag);

    @Query(value = "select * from catering.public.product prod " +
            "inner join catering.public.orders_products op on prod.id = op.products_id " +
            "inner join catering.public.orders o on o.id = op.order_id " +
            "where o.product_order_id = :productOrderId", nativeQuery = true)
    Set<Product> findAllByProductOrderId(@Param("productOrderId") Integer productOrderId);

    @Query(value = "select code from catering.public.product prod order by code desc limit 1", nativeQuery = true)
    Integer findLastProductCode();

}
