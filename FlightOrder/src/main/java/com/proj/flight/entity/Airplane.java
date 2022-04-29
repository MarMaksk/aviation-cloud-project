package com.proj.flight.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

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

}
