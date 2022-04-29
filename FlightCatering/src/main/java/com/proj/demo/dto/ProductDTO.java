package com.proj.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDTO {

    /*
   Код продукта
    */
    private int code;

    private String name;

    /*
    Дата производства
     */
    private LocalDate manufacturedDate;
    /*
    Срок годности
     */
    private int expirationDate;
    /*
    Аллергены, диетический стол и т.д.
     */
    private String tag;
}
