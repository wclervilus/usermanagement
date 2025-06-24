package kalitek.solutions.usermanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kalitek.solutions.common.Auditable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(callSuper = true)
@Setter
@Getter
@Entity
@Table(name = "KS_SESSION")
public class Session extends Auditable implements Identifiable<Long> {
    private Long projectId;
    private Long userId;
    private String refreshTokenHash;
    private LocalDateTime expiresAt;
    private String userAgent;
    private String ip;
}
