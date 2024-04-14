package ru.testRestApi.project.RESTApiLIbraryProject.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Order;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findOrdersByUser_Id(int id);
    @Query(
            "select o from Order o where o.orders_number = :order_id"
    )
    Optional<Order> findOrderByOrders_number(@Param("order_id") int order_id);
}
