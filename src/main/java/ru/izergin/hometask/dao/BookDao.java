package ru.izergin.hometask.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.izergin.hometask.domain.Book;

import java.util.List;

public interface BookDao extends MongoRepository<Book, Long>, BookDaoCustom {
    List<Book> findByGenre(String name);
    void deleteByName(String name);
    void deleteById(String id);
}
