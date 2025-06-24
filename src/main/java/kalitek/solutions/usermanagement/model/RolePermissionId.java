package kalitek.solutions.usermanagement.model;

import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RolePermissionId implements Serializable {
    private Long projectId;
    private Long roleId;
    private Long permissionId;
}
