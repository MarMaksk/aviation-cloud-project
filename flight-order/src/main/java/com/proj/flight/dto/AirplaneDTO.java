package com.proj.flight.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Data
public class AirplaneDTO {

    private String model;

    /*
    Код самолёта
     */
    @Size(max = 3)
    private String iataCode;

    private int loadCapacity;

}
