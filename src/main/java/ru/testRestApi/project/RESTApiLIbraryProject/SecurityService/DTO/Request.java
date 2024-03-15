package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO;

import lombok.Data;

@Data
public class Request {
    private String username;
    private String password;
}
