package com.emirhanarici.human_resources_project.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @PreUpdate
    public void preUpdate() {
        this.updatedBy = getUsernameFromAuthentication();
    }


}
