package ru.izergin.hometask.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.izergin.hometask.domain.Book;

import java.util.List;

public interface BookRepositoryCustom {
    //Вернуть все книги с жанром, начинающимся на указанную букву.
    //Задумка такая, но реализация подкачала, просто ищу по имени, пока испытываю трудности со строчными функциями монги
    @Query("{genre: :#{#genre}}")
    List<Book> findByGenreFirstLetter(@Param("genre") String letter);

    @Query("{id: :#{#id}}")
    Book findById(@Param("id") String id);
}
