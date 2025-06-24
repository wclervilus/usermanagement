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
@Table(name = "KS_PERMISSION")
public class Permission extends Auditable implements Identifiable<Long> {
    private String resource;
    private String action;
    private String description;
}
