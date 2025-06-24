package kalitek.solutions.usermanagement.model;

import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RoleGroupId implements Serializable {
    private Long projectId;
    private Long roleId;
    private Long groupId;
}
