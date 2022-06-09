package com.proj.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aviation.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Product extends AbstractEntity {

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
    private String tags;

}
