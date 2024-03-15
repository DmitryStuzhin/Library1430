package ru.testRestApi.project.RESTApiLIbraryProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;
import ru.testRestApi.project.RESTApiLIbraryProject.services.BooksService;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/books")
public class BooksController {
    private int booksId;
    private final BooksService booksService;


    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/admin")
    public String adminData(){
        return "Admin Data";
    }
    @GetMapping
    public List<Book> getAllBooks(){
        return booksService.findAll();
    }
    @GetMapping("/{id}")
    public Book findOneBookForId(@PathVariable("id") int id){
         booksId = id;
        return booksService.findOne(id);
    }
    @GetMapping("/show")
    public Book showBook(){
        return booksService.findOne(booksId);
    }
}
