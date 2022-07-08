package org.aviation.projects.flightorder.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ExaminationDTO {

    /*
Дата проведения проверки
 */
    @ApiModelProperty(value = "Examination date", example = "2020-01-01T00:00:00")
    private LocalDate date;
    /*
    Описание проверки
     */
    @ApiModelProperty(value = "Examination description", example = "Проверка двигателей, замена трубок бензонасоса, проклейка двигателей")
    private String description;
    /*
Код самолёта
 */
    @Size(max = 3)
    @ApiModelProperty(value = "Airplane code", example = "M01")
    private String iataCode;

}
