package com.proj.flight.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aviation.entity.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@Table(name = "airplane")
public class Airplane extends AbstractEntity {
    private String model;

    /*
    Код самолёта
     */
    @Column(length = 3, unique = true)
    @Pattern(regexp = "[A-Z][0-9][0-9]")
    private String iataCode;

    private int loadCapacity;

    private boolean busy;
}
