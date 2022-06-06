package com.proj.flight.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aviation.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@Table(name = "airport")
public class Airport extends AbstractEntity {
    /* airplane */
    @Column(length = 4, unique = true)
    @Pattern(regexp = "^\\d+$")
    private String icaoCode;

    private String country;

    private String city;

    private boolean deleted;
}
