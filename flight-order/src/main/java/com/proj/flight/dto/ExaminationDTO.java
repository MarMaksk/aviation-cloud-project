package com.proj.flight.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ExaminationDTO {

    /*
Дата проведения проверки
 */
    private LocalDate date;
    /*
    Описание проверки
     */
    private String description;
    /*
Код самолёта
 */
    @Size(max = 3)
    private String iataCode;

}
