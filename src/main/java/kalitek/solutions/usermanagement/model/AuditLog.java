package kalitek.solutions.usermanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kalitek.solutions.common.Auditable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Setter
@Getter
@Entity
@Table(name = "KS_AUDIT_LOG")
public class AuditLog extends Auditable implements Identifiable<Long> {
    private Long projectId;
    private Long userId;
    private String eventType;
    private String entityType;
    private String entityId;
    private String message;
    private String ip;
    private String userAgent;
}
