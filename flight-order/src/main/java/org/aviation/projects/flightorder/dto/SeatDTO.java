package org.aviation.projects.flightorder.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class SeatDTO {

    @Size(max = 3)
    @ApiModelProperty(value = "Seat code", example = "A01")
    private String seatNumber;
    /*
    Бизнес класс
     */
    @ApiModelProperty(value = "Business class", example = "true")
    private boolean business;
    /*
    Код самолёта
     */
    @Size(max = 3)
    @ApiModelProperty(value = "Airplane code", example = "M01")
    private String iataCode;
}
