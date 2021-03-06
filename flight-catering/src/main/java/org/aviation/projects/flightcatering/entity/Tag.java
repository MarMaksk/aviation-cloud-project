package org.aviation.projects.flightcatering.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aviation.projects.commons.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tag extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String name;
}
