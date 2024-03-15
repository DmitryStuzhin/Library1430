package ru.testRestApi.project.RESTApiLIbraryProject.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findById(int id);
}
