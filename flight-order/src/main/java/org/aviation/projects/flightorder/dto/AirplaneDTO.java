package org.aviation.projects.flightorder.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

@Data
public class AirplaneDTO {

    private String model;

    /*
    Код самолёта
     */
    @Size(max = 3)
    @EqualsAndHashCode.Exclude
    private String iataCode;

    private int loadCapacity;

}
