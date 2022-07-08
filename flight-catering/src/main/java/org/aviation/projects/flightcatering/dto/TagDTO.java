package org.aviation.projects.flightcatering.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

    @ApiModelProperty(value = "Tag code", example = "Sugar")
    private String name;

}
