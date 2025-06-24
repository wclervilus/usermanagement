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
@Table(name = "KS_ENDPOINT")
public class Endpoint extends Auditable implements Identifiable<Long> {
    private Long projectId;
    private String path;
    private String method;
    private String resourceTag;
}
