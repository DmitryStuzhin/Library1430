package ru.testRestApi.project.RESTApiLIbraryProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.Role;

@Data
@AllArgsConstructor
public class GetRole {
    private Role role;
}
