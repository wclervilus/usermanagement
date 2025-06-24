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
@IdClass(UserRoleId.class)
@Table(name = "KS_USER_ROLE")
public class UserRole {
    @Id
    private Long projectId;
    @Id
    private Long userId;
    @Id
    private Long roleId;
}
