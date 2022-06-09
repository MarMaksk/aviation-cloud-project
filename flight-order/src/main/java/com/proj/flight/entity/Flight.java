package com.proj.flight.entity;

import com.proj.flight.entity.enums.FlightStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aviation.entity.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "flight")
public class Flight extends AbstractEntity {

    private boolean regular;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    /*
    Полный номер рейса содержит ИАТА и ИКАО код -> длина до 7 символов
     */
    @Column(length = 7, unique = true)
    private String flightNumber;
    /*
    Время вылета
     */
    private LocalDateTime departure;
    /*
    Время полёта
     */
    private LocalTime flightTime;

    private int passengersCount;

    @Column(unique = true)
    private Integer productOrderId;

    private Double ticketPrice;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "dep_airport_id")
    private Airport departureAirport;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Set<Flight> alternativeFlights;

    @PrePersist
    public void prePersist(){
        this.setProductOrderId(new Random().nextInt());
    }

}
