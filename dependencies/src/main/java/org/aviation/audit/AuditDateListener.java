package org.aviation.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

public class AuditDateListener {

    @PrePersist
    public void prePersist(AuditEntity entity) {
        entity.setCreatedAt(Instant.now());
    }

    @PreUpdate
    public void preUpdate(AuditEntity entity) {
        entity.setUpdatedAt(Instant.now());
    }

}
