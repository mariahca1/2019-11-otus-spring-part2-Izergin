package ru.izergin.hometask.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.izergin.hometask.domain.Book;

import java.util.List;

public interface BookDaoCustom {
    //Вернуть все книги с жанром, начинающимся на указанную букву.
    //Задумка такая, но реализация подкачала, просто ищу по имени, пока испытываю трудности со строчными функциями монги
    @Query("{genre: :#{#genre}}")
    List<Book> findByGenreFirstLetter(@Param("genre") String letter);
}
