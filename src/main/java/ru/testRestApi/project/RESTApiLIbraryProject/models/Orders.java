package ru.testRestApi.project.RESTApiLIbraryProject.models;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    @Id
    @Column(name = "id")
    private int id;
    @ManyToMany
    private User user_id;
    @ManyToOne
    @Column(name = "book_id")
    private Book book_id;
}
