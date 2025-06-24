package kalitek.solutions.usermanagement.model;

import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserGroupId implements Serializable {
    private Long projectId;
    private Long userId;
    private Long groupId;
}
