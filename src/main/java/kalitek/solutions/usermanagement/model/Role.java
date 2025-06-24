package kalitek.solutions.usermanagement.model;

import jakarta.persistence.*;
import kalitek.solutions.common.Auditable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Setter
@Getter
@Entity
@Table(name = "KS_ROLE")
public class Role extends Auditable implements Identifiable<Long> {
    private String name;
}

