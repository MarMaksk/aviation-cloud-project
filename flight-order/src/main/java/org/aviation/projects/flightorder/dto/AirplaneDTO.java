package org.aviation.projects.flightorder.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

@Data
public class AirplaneDTO {

    @ApiModelProperty(value = "Airplane model", example = "Airbus A380")
    private String model;

    /*
    Код самолёта
     */
    @Size(max = 3)
    @EqualsAndHashCode.Exclude
    @ApiModelProperty(value = "Airplane code", example = "M01")
    private String icaoCode;

    @ApiModelProperty(value = "Airplane capacity", example = "100")
    private int loadCapacity;

}
