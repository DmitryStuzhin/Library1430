package ru.testRestApi.project.RESTApiLIbraryProject.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Orders;

import java.util.Optional;

@Repository
public interface OrdersRepository  extends JpaRepository<Orders, Integer> {
    Optional<Orders> findByUser_id(int user_id);
}
