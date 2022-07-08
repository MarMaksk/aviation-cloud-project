package org.aviation.projects.flightorder.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class FlightDTO {

    @ApiModelProperty(value = "Flight regular", example = "true")
    private boolean regular;
    /*
    Код самолёта
     */
    @Size(max = 3)
    @ApiModelProperty(value = "Airplane code", example = "M01")
    private String icaoCode;

    /*
    Полный номер рейса содержит ИАТА и ИКАО код -> длина до 7 символов
     */
    @Size(max = 7)
    @ApiModelProperty(value = "Flight number", example = "M011111")
    private String flightNumber;
    /*
    Время вылета
     */
    @ApiModelProperty(value = "Departure time", example = "2020-01-01T00:00:00")
    private LocalDateTime departure;
    /*
    Время полёта
     */
    @ApiModelProperty(value = "Flight time", example = "01:00:00")
    private LocalTime flightTime;

    @ApiModelProperty(value = "Flight passengers", example = "100")
    private int passengersCount;

    @ApiModelProperty(value = "Flight product order id", example = "87654321")
    private Integer productOrderId;

    @ApiModelProperty(value = "Flight status", example = "Выполнен")
    private String status;

    @ApiModelProperty(value = "ticket price", example = "100")
    private Double ticketPrice;

    @Size(max = 4)
    @ApiModelProperty(value = "Airport code departure", example = "1111")
    private String iataCodeDeparture;

    @Size(max = 4)
    @ApiModelProperty(value = "Airport code arrival", example = "1112")
    private String iataCodeArrival;

    @ApiModelProperty(value = "flight number alternative flight", example = "M021111")
    private String flightNumberAltFlight;
}
