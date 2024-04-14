package ru.testRestApi.project.RESTApiLIbraryProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;

@Data
@AllArgsConstructor
public class ActiveOrderDTO {
    private Integer user;
    private Integer book;
    private Integer order_number;
}
