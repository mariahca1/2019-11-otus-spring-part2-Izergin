package ru.izergin.hometask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.izergin.hometask.domain.Book;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);
    Boolean existsByName(String name);
    void deleteByName(String name);
}
