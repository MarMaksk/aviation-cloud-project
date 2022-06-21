package org.aviation.projects.flightcatering.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductDTO {

    /*
   Код продукта
    */
    private int code;

    private String name;

    /*
    Срок годности
     */
    private int expirationDate;
    /*
    Аллергены, диетический стол и т.д.
     */
    private Set<TagDTO> tags;
}
