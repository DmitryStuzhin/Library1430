package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.SecurityRep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
    @Query("select b from Book b inner join b.users a where a.id = :userId")
    Book findBookByUser_Id(@Param("userId") int userId);
}
