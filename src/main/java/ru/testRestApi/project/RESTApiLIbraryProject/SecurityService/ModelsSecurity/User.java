package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "class_number")
    private String class_number;
    @ManyToMany
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id" )
    )
    private Collection<Role> roles;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> booksFromOrder;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "activeorders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> booksFromActiveOrders;

    public User(int id, String username, String password, String class_number, List<Book> books) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.class_number = class_number;
        this.booksFromOrder = books;
    }
}
