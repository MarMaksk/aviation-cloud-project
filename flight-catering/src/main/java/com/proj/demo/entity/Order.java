package com.proj.demo.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aviation.entity.AbstractEntity;
import org.aviation.entity.enums.DeliveryStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
public class Order extends AbstractEntity {
    @Column(name = "icao_code", length = 4)
    private String icaoCode; /* airplane */
    @Column(name = "iata_code", length = 3)
    private String iataCode; /* airport */
    /*
    Крайнее время для поставки
    Надо доставить за 4 часа до конца
     */
    private LocalDateTime lastDate;
    /*
    Время когда доставка была осуществлена
     */
    private LocalDateTime deliveryTime;
    /*
    Статус доставки
     */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    /*
    Идентификатор с другого сервиса
     */
    @Column(unique = true)
    private Integer productOrderId;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    @JoinColumn(name = "product_id")
    private Set<Product> products;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    @JoinColumn(name = "product_id")
    private Set<Product> deliveredProducts;
}
