package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO;


import lombok.Data;

import java.util.Collection;

@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private String class_number;
}
