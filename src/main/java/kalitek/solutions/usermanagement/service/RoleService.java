package kalitek.solutions.usermanagement.service;

import kalitek.solutions.usermanagement.model.Role;
import kalitek.solutions.usermanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class RoleService extends GenericService<Role, Long> {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super(roleRepository, "role");
        this.roleRepository = roleRepository;
    }
}