package ru.testRestApi.project.RESTApiLIbraryProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.IdGeneratorType;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "id", insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "year_published")
    private int year_published;
    @Column(name = "image_url")
    private String image_url;
    @Column(name = "description")
    private String description;
    @Column(name = "genre")
    private String genre;
    @Column(name = "quantity")
    private int quantity;
    @ManyToMany(mappedBy = "booksFromOrder")
    private Collection<User> users;
    @ManyToMany(mappedBy = "booksFromActiveOrders")
    private Collection<User> usersA;

    public Book(int id, String title, int year_published, String image_url, String description, String genre, int quantity) {
        this.id = id;
        this.title = title;
        this.year_published = year_published;
        this.image_url = image_url;
        this.description = description;
        this.genre = genre;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear_published() {
        return year_published;
    }

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
