package org.aviation.projects.commons.audit;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditDateListener.class)
public class AuditEntity {

    @Column(updatable = false)
    private Instant createdAt;

    @Column(updatable = false)
    private Instant updatedAt;
}
