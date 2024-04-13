package ru.testRestApi.project.RESTApiLIbraryProject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.Book_id;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.User_id;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Services.ServiceUser;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Order;
import ru.testRestApi.project.RESTApiLIbraryProject.services.BooksService;
import ru.testRestApi.project.RESTApiLIbraryProject.services.OrderService;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BooksService booksService;
    private final ServiceUser userService;
    private final OrderService orderService;

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
        return booksService.findOne(id);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody Book_id order){
        return orderService.createOrder(order);
    }
    @GetMapping("/getUserInfo")
    public ResponseEntity<?> getUserInfo(){
        return userService.getUserInfo();
    }
    @PostMapping("/deleteOrder")
    public ResponseEntity<?> deleteOrder(){
        return orderService.deleteOrder();
    }
}
