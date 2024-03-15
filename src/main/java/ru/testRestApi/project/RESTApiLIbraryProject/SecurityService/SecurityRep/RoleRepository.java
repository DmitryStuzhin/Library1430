package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.SecurityRep;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getRoleByName(String name);
}
