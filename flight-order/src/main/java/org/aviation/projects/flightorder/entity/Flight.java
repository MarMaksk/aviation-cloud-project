package org.aviation.projects.flightorder.entity;

import org.aviation.projects.flightorder.entity.enums.FlightStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aviation.projects.commons.entity.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@Table(name = "flight")
public class Flight extends AbstractEntity {

    @Column(updatable = false)
    private boolean regular;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alt_flight_id")
    private Flight alternativeFlights;

    @PrePersist
    public void prePersist() {
        this.setProductOrderId(new Random().nextInt());
    }

}
