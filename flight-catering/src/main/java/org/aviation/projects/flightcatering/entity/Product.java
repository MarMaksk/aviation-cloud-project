package org.aviation.projects.flightcatering.entity;

import lombok.*;
import org.aviation.projects.commons.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product extends AbstractEntity {

    /*
    Код продукта
     */
    @Column(unique = true, updatable = false)
    private int code;

    private String name;

    /*
    Срок годности
     */
    private int expirationDate;
    /*
    Аллергены, диетический стол и т.д.
     */
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Tag> tags;

}
