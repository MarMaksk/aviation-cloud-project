package com.proj.flight.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aviation.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "airport")
public class Airport extends AbstractEntity {
    /* airplane */
    @Column(length = 4)
    private String icaoCode;

    private String country;

    private String city;

    private boolean deleted;
}
