package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.SecurityRep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
}
