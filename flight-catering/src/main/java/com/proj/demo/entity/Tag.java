package com.proj.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aviation.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Data
public class Tag extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String name;
}
