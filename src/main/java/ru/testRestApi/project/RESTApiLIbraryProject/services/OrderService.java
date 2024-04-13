package ru.testRestApi.project.RESTApiLIbraryProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.Book_id;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Services.ServiceUser;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Order;
import ru.testRestApi.project.RESTApiLIbraryProject.repositoryes.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final Integer orderUniqueNumber = 13579;
    private final OrderRepository orderRepository;
    private final ServiceUser userService;
    private final BooksService booksService;
    @Autowired
    public OrderService(OrderRepository orderRepository, ServiceUser userRepository, BooksService booksService) {
        this.orderRepository = orderRepository;
        this.userService = userRepository;
        this.booksService = booksService;
    }

    public void save(ru.testRestApi.project.RESTApiLIbraryProject.models.Order order) { orderRepository.save(order); }

    public Optional<Order> findOrderByUser_Id(int user_id){
        return orderRepository.findOrdersByUser_Id(user_id);
    }
    public Order deleteOrderByOrder(Order order) {
        return orderRepository.deleteOrder(order);
    }

    public ResponseEntity<?> createOrder(@RequestBody Book_id book_id){
        Order order= null;
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null  && authentication.getName() != null) {
            User user = userService.findUserByUsername(authentication.getName()).orElse(null);
            if(user.getBooks().isEmpty()){
                order =  new Order(user, booksService.findOne(book_id.getBook_id()), orderUniqueNumber - 2);
                save(order);
            }else {
                return ResponseEntity.badRequest().body("У вас уже есть книга");
            }
        }
        return ResponseEntity.ok(order);
    }
    public ResponseEntity<?> deleteOrder(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(authentication.getName()).orElse(null);
        Order order = findOrderByUser_Id(user.getId()).orElse(null);
        return ResponseEntity.ok(deleteOrderByOrder(order).getOrder_number());
    }
}
