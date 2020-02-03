package ru.izergin.hometask.controller;

import lombok.val;
import org.h2.tools.Console;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.domain.BookComment;
import ru.izergin.hometask.domain.Genre;
import ru.izergin.hometask.service.AuthorServiceImpl;
import ru.izergin.hometask.service.BookServiceImpl;
import ru.izergin.hometask.service.GenreServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    private final BookServiceImpl bookService;
    private final AuthorServiceImpl authorService;
    private final GenreServiceImpl genreService;

    public MainController(BookServiceImpl bookService, AuthorServiceImpl authorService, GenreServiceImpl genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() throws SQLException {

        System.out.println("Books count on start = " + bookService.getCount().toString());
        Genre genre1 = genreService.save(new Genre("Classic"));
        Genre genre2 = genreService.save(new Genre("Horror"));

        //демонстрация базовых методов Book
        //создание 2х книг
        Book book1 = new Book().setName("book1").setGenre(genre1);
        bookService.save(book1);
        Book book2 = new Book().setName("book2").setGenre(genre1);
        book2 = bookService.save(book2);
        System.out.println("Books count after books added = " + bookService.getCount().toString());

        //удаление книги
        System.out.println(book2.getId());
        bookService.deleteById(book2.getId());
        System.out.println("Books count after book deleted = " + bookService.getCount().toString());

        //изменение книги
        book1.setName("book1_new").setGenre(genre2);
        bookService.updateNameById(book1.getId(), book1);
        bookService.updateGenreById(book1.getId(), book1);

        Book book3 = bookService.getBookByName("book1_new").get();
        System.out.println(book3);

        System.out.println("all books list");
        List<Book> bookList = bookService.getAll();
        for (Book book : bookList) {
            System.out.println(book);
        }

        //демонстрация работы с комментариями
        List<BookComment> bookComments = new ArrayList<>();
        bookComments.add(new BookComment("good"));
        bookComments.add(new BookComment("good1"));

        Book book4 = bookService.getBookByName("book1_new").get();
        book4.setComments(bookComments);
        bookService.save(book4);

        Book book5 = bookService.getBookByName("book1_new").get();
        System.out.println(book5.toString());

     //   Console.main();
    }
}
