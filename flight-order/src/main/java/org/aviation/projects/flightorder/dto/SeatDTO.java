package org.aviation.projects.flightorder.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class SeatDTO {
    @Size(max = 3)
    private String seatNumber;
    /*
    Бизнес класс
     */
    private boolean business;
    /*
    Код самолёта
     */
    @Size(max = 3)
    private String iataCode;
}
