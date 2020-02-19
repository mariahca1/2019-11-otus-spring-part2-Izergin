package ru.izergin.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import ru.izergin.hometask.domain.Author;
import ru.izergin.hometask.domain.Book;
//import ru.izergin.hometask.domain.Genre;
import ru.izergin.hometask.dto.BookDto;
//import ru.izergin.hometask.service.AuthorService;
//import ru.izergin.hometask.service.BookService;
import ru.izergin.hometask.service.AuthorServiceImpl;
import ru.izergin.hometask.service.BookServiceImpl;
//import ru.izergin.hometask.service.GenreService;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private AuthorServiceImpl authorService;

    private final String GENRE_CLASSIC = "Classic";
    private final String GENRE_COMEDY = "Comedy";
    private final String GENRE_HORROR = "Horror";
    private final String GENRE_ACTION = "Action";

    private final String BOOK_NAME_1 = "book1";
    private final String BOOK_NAME_2 = "book2";
    private final String BOOK_NAME_3 = "book3";
    private final String BOOK_NAME_4 = "book4";

    @EventListener(ApplicationReadyEvent.class)
    public void start() throws SQLException {
        System.out.println("begin");
        //демонстрация базовых методов Book

        //удалить всё что есть для локальной БД
        authorService.deleteAll();
        bookService.deleteAll();

        System.out.println("Add 3 books");
        Book book1 = bookService.save(new BookDto(BOOK_NAME_1, GENRE_CLASSIC, 5).addAuthor(new Author("q", "qw")));
        Book book2 = bookService.save(new BookDto(BOOK_NAME_2, GENRE_CLASSIC, 10).addAuthor(new Author("q", "qw")));
        Book book3 = bookService.save(new BookDto(BOOK_NAME_3, GENRE_HORROR, 15).addAuthor(new Author("q", "qw")));
        Book book4 = bookService.save(new BookDto(BOOK_NAME_4, GENRE_COMEDY, 20));
        //попытка сохранения книги с уже существующим в базе названием
        try {
            Book book5 = bookService.save(new BookDto(BOOK_NAME_1, GENRE_HORROR, 25));
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Books count after books added = " + bookService.getCount().toString());
        printAllBooks();

        //вывод всех книг данного жанра
        System.out.println("Get all books with genre = " + GENRE_CLASSIC);
        for (Book book : bookService.findByGenre(GENRE_CLASSIC)) {
            System.out.println(book.toString());
        }

        //удаление книги
        System.out.println("del book2");
        bookService.deleteByName(book2.getName());
        System.out.println("Books count after book deleted = " + bookService.getCount().toString());
        printAllBooks();

        //изменение книги
        System.out.println("update book1 name to book1_upd and genre to action");
        book1.setName("book1_upd").setGenre(GENRE_ACTION);
        bookService.update(book1);
        printAllBooks();

        //демонстрация работы с комментариями
        book1.addComment("Good one");
        book1.addComment("Good one2").addAuthor(new Author("q123", "qw123"));
        bookService.update(book1);
        printAllBooks();

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
