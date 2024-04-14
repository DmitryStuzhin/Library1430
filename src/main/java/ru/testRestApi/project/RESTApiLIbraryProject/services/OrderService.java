package ru.testRestApi.project.RESTApiLIbraryProject.services;

import lombok.RequiredArgsConstructor;
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
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final List<Integer> list = new Random()
            .ints( 10000, 20000)
            .distinct()
            .limit(1000)
            .boxed()
            .collect(Collectors.toList());

    private final OrderRepository orderRepository;
    private final ServiceUser userService;
    private final BooksService booksService;

    public Integer getUniqueId(){
        int randomNum = new Random().nextInt(list.size());
        int id = list.get(randomNum);
        list.remove(randomNum);
        return id;
    }

    public void save(Order order) { orderRepository.save(order); }

    public Order findOrderByOrder_Number(int id) { return orderRepository.findOrderByOrders_number(id).orElse(null); }

    public Optional<Order> findOrderByUser_Id(int user_id){
        return orderRepository.findOrdersByUser_Id(user_id);
    }

    public ResponseEntity<?> createOrder(@RequestBody Book_id book_id){
        Order order= null;
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null  && authentication.getName() != null) {
            User user = userService.findUserByUsername(authentication.getName()).orElse(null);
            if(user.getBooksFromOrder().isEmpty()){
                order =  new Order(user, booksService.findOne(book_id.getBook_id()), getUniqueId());
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
        orderRepository.delete(order);
        if(user.getBooksFromOrder().isEmpty()){
            return ResponseEntity.ok("All booksFromOrder are deleted");
        }
        else {
            return ResponseEntity.ok(user.getBooksFromOrder().get(0).getTitle());
        }
    }
//    public ResponseEntity<?> addActiveOrder(@RequestBody Order order){
//
//    }
}
