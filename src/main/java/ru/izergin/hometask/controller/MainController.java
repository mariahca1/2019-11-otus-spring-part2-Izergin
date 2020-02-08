package ru.izergin.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.domain.Genre;
import ru.izergin.hometask.dto.BookDto;
import ru.izergin.hometask.service.AuthorService;
import ru.izergin.hometask.service.BookService;
import ru.izergin.hometask.service.GenreService;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;

    private final String GENRE_CLASSIC = "Classic";
    private final String GENRE_HORROR = "Horror";

    private final String BOOK_NAME_1 = "book1";
    private final String BOOK_NAME_2 = "book2";
    private final String BOOK_NAME_3 = "book3";

    @EventListener(ApplicationReadyEvent.class)
    public void start() throws SQLException {

        System.out.println("Books count on start = " + bookService.getCount().toString());
        Genre genre1 = genreService.save(GENRE_CLASSIC);
        Genre genre2 = genreService.save(GENRE_HORROR);

        //демонстрация базовых методов Book
        System.out.println("Add 3 books");
        Book book1 = bookService.save(new BookDto(BOOK_NAME_1, GENRE_CLASSIC));
        Book book2 = bookService.save(new BookDto(BOOK_NAME_2, GENRE_CLASSIC));
        Book book3 = bookService.save(new BookDto(BOOK_NAME_3, GENRE_HORROR));
        System.out.println("Books count after books added = " + bookService.getCount().toString());

        //вывод всех книг данного жанра
        System.out.println("Get all books with genre = " + GENRE_CLASSIC);
        Genre genre3 = genreService.getByName(GENRE_CLASSIC).get();
        for (Book book : genre3.getBookList()) {
            System.out.println(book.toString());
        }

        //удаление книги
        System.out.println("del book2");
        bookService.delete(book2.getName());
        System.out.println("Books count after book deleted = " + bookService.getCount().toString());

        //изменение книги
        book1.setName("book1_upd").setGenre(genre2);
        bookService.update(book1);

        //демонстрация работы с комментариями
        book1.addComment("Good one");
        book1.addComment("Good one2");
        bookService.update(book1);

        //вывод всех книг
        System.out.println("all books list:");
        List<Book> bookList = bookService.getAll();
        for (Book book : bookList) {
            System.out.println(book);
        }
        //   Console.main();
    }
}
