package org.aviation.projects.flightorder.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AirportDTO {

    @Size(max = 4)
    @ApiModelProperty(value = "Airport code", example = "1111")
    private String icaoCode;

    @ApiModelProperty(value = "Airport country", example = "Russia")
    private String country;

    @ApiModelProperty(value = "Airport city", example = "Moscow")
    private String city;

}
