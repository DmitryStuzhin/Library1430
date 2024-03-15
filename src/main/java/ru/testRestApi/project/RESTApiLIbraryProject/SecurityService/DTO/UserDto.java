package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private int id;
    private String username;
    private String class_number;
}
