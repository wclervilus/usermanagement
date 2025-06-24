package kalitek.solutions.usermanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@IdClass(RoleGroupId.class)
@Table(name = "KS_ROLE_GROUP")
public class RoleGroup {
    @Id
    private Long projectId;
    @Id
    private Long roleId;
    @Id
    private Long groupId;
}
