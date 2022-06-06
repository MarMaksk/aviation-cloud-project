package com.proj.flight.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
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
