package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.Role;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.SecurityRep.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role getRole(){
        return roleRepository.getRoleByName("ROLE_USER");
    }
}
