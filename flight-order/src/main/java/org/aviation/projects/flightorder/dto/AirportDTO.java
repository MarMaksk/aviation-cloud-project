package org.aviation.projects.flightorder.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AirportDTO {

    @Size(max = 4)
    private String icaoCode;

    private String country;

    private String city;

}
