package org.aviation.audit;

import lombok.Data;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditDateListener.class)
public class AuditEntity {
    private Instant createdAt;

    private Instant updatedAt;
}
