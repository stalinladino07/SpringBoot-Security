package com.qph.hacienda.configuration.security;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
//@EntityListeners(EntityAuditListener.class)
public abstract class EntityAudit {
	@Column(insertable = true, updatable = false, nullable = true)
	protected LocalDateTime createdDate;

	@Column(insertable = true, updatable = false, nullable = true, length = 50)
	protected String createdBy;

	@Column(insertable = false, updatable = true, nullable = true)
	protected LocalDateTime modifiedDate;

	@Column(insertable = false, updatable = true, nullable = true, length = 50)
	protected String modifiedBy;

	@Column(nullable = false, columnDefinition = "boolean DEFAULT false")
	protected Boolean deleted = Boolean.FALSE;

	@Column(nullable = false, columnDefinition = "boolean DEFAULT true")
	protected Boolean state = Boolean.TRUE;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDate = LocalDateTime.now();
    }

}

