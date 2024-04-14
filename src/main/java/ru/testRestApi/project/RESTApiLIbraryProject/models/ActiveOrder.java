package ru.testRestApi.project.RESTApiLIbraryProject.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;


@Entity
@Table(name = "activeorders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;

    public ActiveOrder(User user, Book book, Integer orderNumber) {
        this.user = user;
        this.book = book;
        this.orderNumber = orderNumber;
    }
}