package com.proj.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aviation.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
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
    Срок годности
     */
    private int expirationDate;
    /*
    Аллергены, диетический стол и т.д.
     */
    @ManyToMany
    @JoinTable
    @ToString.Exclude
    private Set<Tag> tags;

}
