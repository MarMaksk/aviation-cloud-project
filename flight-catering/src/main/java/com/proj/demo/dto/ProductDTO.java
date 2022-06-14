package com.proj.demo.dto;

import com.proj.demo.entity.Tag;
import lombok.Data;

import java.time.LocalDate;
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
