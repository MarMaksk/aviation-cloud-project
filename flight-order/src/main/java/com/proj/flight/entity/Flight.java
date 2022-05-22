package com.proj.flight.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aviation.entity.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "flight")
public class Flight extends AbstractEntity {

    private boolean regular;

    @OneToOne
    @JoinColumn(name = "airplane_id")
    public Airplane airplane;

    /*
    Полный номер рейса содержит ИАТА и ИКАО код -> длина до 7 символов
     */
    @Column(length = 7)
    private String flightNumber;
    /*
    Время вылета
     */
    private LocalDateTime departure;
    /*
    Время полёта
     */
    private double flightTime;

    private int passengersCount;

    private Long productOrderId;

    private Double ticketPrice;

    private boolean deleted;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_airport_id")
    private Airport departureAirport;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Set<Flight> alternativeFlights;

}
