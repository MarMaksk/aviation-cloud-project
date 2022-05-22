package com.proj.flight.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aviation.entity.AbstractEntity;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "airplane")
public class Airplane extends AbstractEntity {
    private String model;

    /*
    Код самолёта
     */
    @Column(length = 3)
    private String iataCode;

    private int loadCapacity;

    private boolean deleted;
}
