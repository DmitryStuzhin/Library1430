package ru.testRestApi.project.RESTApiLIbraryProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Orders;
import ru.testRestApi.project.RESTApiLIbraryProject.repositoryes.OrdersRepository;

import java.util.Optional;

@Service
public class
OrderService {
    private  final OrdersRepository ordersRepository;

    @Autowired
    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
    public void save(Orders orders){ ordersRepository.save(orders); }

    public Orders findByUserId(int user_id){
        return ordersRepository.findByUser_id(user_id).orElse(null) ;
    }
}
