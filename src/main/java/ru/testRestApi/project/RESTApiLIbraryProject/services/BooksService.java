package ru.testRestApi.project.RESTApiLIbraryProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;
import ru.testRestApi.project.RESTApiLIbraryProject.repositoryes.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(){ return booksRepository.findAll(); }

    public Book findOne(int id){
        Optional<Book> oneBook =
                booksRepository.findById(id);
        return oneBook.orElse(null);
    }
//    public Book findBooksByUser_Id(int id){
//        return booksRepository.findBooksByUser_Id(id).orElse(null);
//    }
}
