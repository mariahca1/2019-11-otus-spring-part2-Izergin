package ru.izergin.hometask.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.izergin.hometask.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Long>, BookRepositoryCustom {
    List<Book> findByGenre(String name);
    void deleteByName(String name);
    void deleteById(String id);
}
