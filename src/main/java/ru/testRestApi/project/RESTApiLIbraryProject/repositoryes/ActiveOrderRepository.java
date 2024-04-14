package ru.testRestApi.project.RESTApiLIbraryProject.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.testRestApi.project.RESTApiLIbraryProject.models.ActiveOrder;

import java.util.Optional;

@Repository
public interface ActiveOrderRepository extends JpaRepository<ActiveOrder, Integer> {

    Optional<ActiveOrder> findActiveOrdersByOrderNumber(Integer order_number);
}
