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
@Table(name = "seat")
public class Seat extends AbstractEntity {

    @Column(length = 3)
    private String seatNumber;
    /*
    Бизнес класс
     */
    private boolean business;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;
}