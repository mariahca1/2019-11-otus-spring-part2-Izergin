package ru.izergin.hometask.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import ru.izergin.hometask.domain.Author;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.dto.BookDto;
import ru.izergin.hometask.service.AuthorServiceImpl;
import ru.izergin.hometask.service.BookServiceImpl;

import java.sql.SQLException;
import java.util.List;

//@Controller
@AllArgsConstructor
public class MainController {
    private final BookServiceImpl bookService;
    private final AuthorServiceImpl authorService;

    private final String GENRE_CLASSIC = "Classic";
    private final String GENRE_COMEDY = "Comedy";

    private final String BOOK_NAME_1 = "Война и мир";
    private final String BOOK_NAME_2 = "Азбука";

//    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        //удалить всё что есть для локальной БД
        authorService.deleteAll();
        bookService.deleteAll();

        Book book1 = bookService.save(new BookDto(BOOK_NAME_1, GENRE_CLASSIC, 5)
                .addAuthor(new Author("Иван", "Иванов"))
                .addAuthor(new Author("Петр", "Петров"))
                .addComment("Комментарий 1")
                .addComment("Комментарий 2"));
        Book book2 = bookService.save(new BookDto(BOOK_NAME_2, GENRE_COMEDY, 10)
                .addAuthor(new Author("Сидр", "Сидоров"))
                .addAuthor(new Author("Вертолет", "Вертолетов"))
                .addComment("Комментарий 3")
                .addComment("Комментарий 4"));
    }

    public void start() throws SQLException {
        //Демонстрация работы кастомного метода
        List<Book> bookList = bookService.findByGenreFirstLetter("Horror");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    void printAllBooks() {
        //вывод всех книг
        System.out.println("all books list:");
        List<Book> bookList = bookService.getAll();
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
}
