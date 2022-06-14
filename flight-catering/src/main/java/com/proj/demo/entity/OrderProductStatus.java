package com.proj.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aviation.entity.AbstractEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "order_product_status")
@NoArgsConstructor
public class OrderProductStatus extends AbstractEntity {

    @OneToMany
    private List<Order> orders;

    @ManyToOne
    private Product product;

    private boolean status;

}
