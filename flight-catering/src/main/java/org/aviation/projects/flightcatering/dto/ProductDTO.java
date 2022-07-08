package org.aviation.projects.flightcatering.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
public class ProductDTO {

    /*
   Код продукта
    */
    @ApiModelProperty(value = "Product code", example = "1")
    private int code;

    @ApiModelProperty(value = "Product name", example = "Product 1")
    private String name;

    /*
    Срок годности
     */
    @ApiModelProperty(value = "Product expiration date", example = "2020-01-01T00:00:00")
    private int expirationDate;
    /*
    Аллергены, диетический стол и т.д.
     */
    @ApiModelProperty(value = "Product tags", example = "Аллергены")
    private Set<TagDTO> tags;
}
