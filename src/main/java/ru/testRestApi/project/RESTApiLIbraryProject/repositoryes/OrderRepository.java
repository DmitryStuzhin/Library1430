package ru.testRestApi.project.RESTApiLIbraryProject.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Order;

import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findOrdersByUser_Id(int id);

    Order deleteOrder(Order order);
}
