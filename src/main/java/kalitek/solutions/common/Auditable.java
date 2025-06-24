package kalitek.solutions.common;

import jakarta.persistence.*;
import kalitek.solutions.usermanagement.model.Status;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@RequiredArgsConstructor
@Setter
@Getter
@MappedSuperclass
public abstract class Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "description")
    protected String description;

    @Column(name = "created_by", updatable = false)
    protected String createdBy;

    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "updated_by")
    protected String updatedBy;

    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    @Column(name = "status")
    protected Status status;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.createdBy = getCurrentUser();
        this.updatedBy = this.createdBy;
        if (this.status == null) {
            this.status = Status.ACTIVE;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = getCurrentUser();
    }
    protected String getCurrentUser() {
        return "system";
    }
}
