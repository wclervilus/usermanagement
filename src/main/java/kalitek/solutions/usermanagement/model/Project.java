package kalitek.solutions.usermanagement.model;

import jakarta.persistence.Column;
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
@Table(name = "KS_PROJECT")
public class Project extends Auditable implements Identifiable<Long> {
    @Column(nullable = false, unique = true)
    private String code;
    private String name;
    private String urlReception;
    private String urlRetour;
    private String description;
    private String logoUrl;
    private Long ownerId;
    private String authStrategy;
    private Integer tokenExpiration;
    private Boolean allowSignup;
    private String defaultLocale;
    private Boolean isActive;
}
