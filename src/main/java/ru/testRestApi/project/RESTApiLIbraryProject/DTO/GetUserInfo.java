package ru.testRestApi.project.RESTApiLIbraryProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.Role;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Orders;

import java.util.Collection;

@Data
@AllArgsConstructor
public class GetUserInfo {
    private int id;
    private String username;
    private String password;
    private String class_number;
    private Orders order;
}
