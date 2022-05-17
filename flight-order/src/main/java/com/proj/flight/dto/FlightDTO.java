package com.proj.flight.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class FlightDTO {

    private boolean regular;
    /*
    Код самолёта
     */
    @Size(max = 3)
    private String iataCode;

    /*
    Полный номер рейса содержит ИАТА и ИКАО код -> длина до 7 символов
     */
    @Size(max = 7)
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

    @Size(max = 4)
    private String icaoCodeDeparture;

    @Size(max = 4)
    private String icaoCodeArrival;
}