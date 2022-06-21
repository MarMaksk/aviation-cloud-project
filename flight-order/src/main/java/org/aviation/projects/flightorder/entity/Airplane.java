package org.aviation.projects.flightorder.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aviation.projects.commons.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@Table(name = "airplane")
public class Airplane extends AbstractEntity {
    private String model;

    /*
    Код самолёта
     */
    @Column(length = 3, unique = true)
    @Pattern(regexp = "[A-Z][0-9][0-9]")
    private String iataCode;

    private int loadCapacity;

    private boolean busy;
}
