package ru.testRestApi.project.RESTApiLIbraryProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.CreateOrder;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.GetUserInfo;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO.GetOrderId;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Services.ServiceUser;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Order;
import ru.testRestApi.project.RESTApiLIbraryProject.services.BooksService;
import ru.testRestApi.project.RESTApiLIbraryProject.services.OrderService;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final ServiceUser userService;
    private final OrderService orderService;

    @Autowired
    public BooksController(BooksService booksService, ServiceUser userService, OrderService orderService) {
        this.booksService = booksService;
        this.userService = userService;
        this.orderService = orderService;
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
        return booksService.findOne(id);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrder book_id){
        Order order = null;
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null  && authentication.getName() != null) {
            User user = userService.findUserByUsername(authentication.getName()).orElse(null);
            if(user.getBooks().isEmpty()){
                order =  new Order(user, booksService.findOne(book_id.getBook_id()));
                orderService.save(order);
            }else ResponseEntity.ok("У вас уже есть книга");
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<?> getUserInfo(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            User user = userService.findUserByUsername(authentication.getName()).orElseThrow(
                    () -> new UsernameNotFoundException("User not found")
            );
            Book userBook = user
                    .getBooks()
                    .stream()
                    .findFirst()
                    .orElse(null);
            return ResponseEntity.ok(new GetUserInfo(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getClass_number(),
                    userBook,
                    new GetOrderId().getOrderId()
            ));
        }
        else return null;
    }
    @GetMapping("/user")
    public ResponseEntity<?> getInfoBook(@RequestBody CreateOrder order){
        User user = userService.findUserById(order.getBook_id()).orElseThrow(()
                -> new UsernameNotFoundException("User not found"));
        return ResponseEntity.ok(user
                .getBooks()
                .stream()
                .toList()
                .get(0)
        );
    }
}
