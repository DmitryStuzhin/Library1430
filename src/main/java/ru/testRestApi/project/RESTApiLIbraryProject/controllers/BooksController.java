package ru.testRestApi.project.RESTApiLIbraryProject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.ActiveOrderDTO;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.Book_id;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.GetRole;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Services.ServiceUser;
import ru.testRestApi.project.RESTApiLIbraryProject.models.ActiveOrder;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;
import ru.testRestApi.project.RESTApiLIbraryProject.services.ActiveOrderService;
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
    private final ActiveOrderService activeOrderService;

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

    @GetMapping("checkRole")
    public ResponseEntity<?> getInf(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if(authentication != null){
            user = userService.findUserByUsername(authentication.getName()).orElseThrow(
                    () -> new UsernameNotFoundException("User not found")
            );

        }
        return ResponseEntity.ok(new GetRole(
                user.getRoles()
                        .stream()
                        .toList()
                        .get(0)
        ));
    }
    @PostMapping("activeOrder")
    public ResponseEntity<?> newActiveOrder(@RequestBody ActiveOrderDTO activeOrder){
        User user = userService.findUserById(activeOrder.getUser()).orElseThrow(
                ()-> new UsernameNotFoundException("User not found")
        );
        Book book = booksService.findOne(activeOrder.getBook());
        activeOrderService.save(new ActiveOrder(user, book, activeOrder.getOrder_number()));
        return ResponseEntity.ok("success");
    }
}