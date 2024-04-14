package ru.testRestApi.project.RESTApiLIbraryProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.Role;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfo {
    private int id;
    private String username;
    private String password;
    private String class_number;
    private Book book;
    private Integer order_number;
    private Role role;
    private boolean isActiveOrder;

    public GetUserInfo(int id, String username, String password, String class_number, Book book) {
        this.username = username;
        this.password = password;
        this.class_number = class_number;
        this.book = book;
    }


    public GetUserInfo(int id, String username, String password, String class_number, Book book, Integer order_number) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.class_number = class_number;
        this.book = book;
        this.order_number = order_number;
    }

    public GetUserInfo(int id, String username, String password, String classNumber, Integer ordersNumber, Book book, boolean isActiveOrder) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.class_number = classNumber;
        this.order_number = ordersNumber;
        this.book = book;
        this.isActiveOrder = isActiveOrder;
    }

}
