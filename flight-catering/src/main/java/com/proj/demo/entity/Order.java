package com.proj.demo.entity;


import com.proj.demo.enums.DeliveryStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aviation.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

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
    private DeliveryStatusEnum status;
}
