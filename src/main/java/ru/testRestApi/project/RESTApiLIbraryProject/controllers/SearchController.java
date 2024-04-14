package ru.testRestApi.project.RESTApiLIbraryProject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.Book_name;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.GetUserInfo;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.OrderNumber;
import ru.testRestApi.project.RESTApiLIbraryProject.models.ActiveOrder;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;
import ru.testRestApi.project.RESTApiLIbraryProject.services.ActiveOrderService;
import ru.testRestApi.project.RESTApiLIbraryProject.services.BooksService;
import ru.testRestApi.project.RESTApiLIbraryProject.services.OrderService;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("search")
public class SearchController {
    private final BooksService booksService;
    private final OrderService orderService;
    private final ActiveOrderService activeOrderService;

    @PostMapping("book")
    public ResponseEntity<?> searchBook(@RequestBody Book_name bookName) {
        return ResponseEntity.ok(booksService.findByTitle(bookName.getBook_name()));
    }
    @PostMapping("order")
    public ResponseEntity<?> searchOrder(@RequestBody OrderNumber orderNumber) {
        User user = orderService.findOrderByOrder_Number(orderNumber.getOrderNumber()).getUser();
        Book book = user.getBooksFromOrder().get(0);
        Boolean isActiveOrder = activeOrderService.isActiveOrderByOrder_Number(orderNumber.getOrderNumber()) != null;

        return ResponseEntity.ok(new GetUserInfo(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getClass_number(),
                orderService.findOrderByUser_Id(user.getId()).get().getOrders_number(),
                new Book(
                        book.getId(),
                        book.getTitle(),
                        book.getYear_published(),
                        book.getImage_url(),
                        book.getDescription(),
                        book.getGenre(),
                        book.getQuantity()
                ),
                isActiveOrder
        ));
    }
}
