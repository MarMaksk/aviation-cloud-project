package org.aviation.projects.flightorder.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aviation.projects.commons.entity.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "examination")
public class Examination extends AbstractEntity {

    /*
    Дата проведения проверки
     */
    private LocalDate date;
    /*
    Описание проверки
     */
    private String description;
    /*
    Самолёт
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @PrePersist
    public void prePersist() {
        if (date == null)
            date = LocalDate.now();
    }
}
