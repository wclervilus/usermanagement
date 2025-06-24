package kalitek.solutions.usermanagement.handler;

import kalitek.solutions.usermanagement.model.Role;
import kalitek.solutions.usermanagement.service.RoleService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;


@Component
public class RoleHandler extends GenericHandler<Role, Long> {

    private final RoleService roleService;
    public RoleHandler(RoleService roleService) {
        super(roleService, "Role", new ParameterizedTypeReference<>() {});
        this.roleService = roleService;
        System.err.println(">>> Initialisation de RoleHandler");
    }
}