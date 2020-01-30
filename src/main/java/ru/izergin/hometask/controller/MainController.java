package ru.izergin.hometask.controller;

import lombok.val;
import org.h2.tools.Console;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
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


//        Optional<Genre> og = genreService.getByName("Classic");
//        Genre genre = og.orElse(new Genre("Classic"));


//        Genre genre = genreService.getByName("Classic1").orElse(new Genre("qwe123"));


//        Genre genre = genreService.getByName("Classic").orElse(genreService.save(new Genre("Classic")));
        Book book = new Book().setName("book1").setGenre(new Genre("Classic"));
        bookService.save(book);



        //String name = Optional.empty().orElse("Other").toString();

        //Optional<Genre> og = genreService.getByName("Classic1");
        //Genre genre2 = og.orElse(genreService.save(new Genre("Classic1")));
//        Book book2 = new Book().setName("qwe1").setGenre(genre2);

//        bookService.save(book2);
//

//
//        Genre genre1 = new Genre().setName("Classic1");
//        Book book1 = new Book().setName("qwe1").setGenre(genre1);
//        bookService.save(book1);


        Console.main();
    }



//        System.out.println("Start");
//        Console.main();

        //        System.out.println(genre.ToString());
//        genreService.save(genre);
        /*System.out.println(genre.ToString());

        genre.setName("Classic1");
        genreService.save(genre);
        System.out.println(genre.ToString());

        genre = genreService.getByName("Classic").orElse(new Genre("Classic"));
        System.out.println(genre.ToString());*/

//Book book = new Book("qwe", new Genre("G"));
//        Book book = new Book("qwe", genre);





//        val genre1 = new Genre("qwe");
//        val book1 = new Book("qwe1",genre1);
//        bookService.save(book1);

//        BookComment bookComment = new BookComment().setComment("shit");
//        List<BookComment> bookCommentList = new ArrayList<>();
//        bookCommentList.add(bookComment);

        //Book book = new Book().setId(1).setName("qwe").setAuthorId(new ArrayList<Integer>()).setGenreId(new ArrayList<Integer>());
//        Book book = new Book().setName("qwe").setGenre(genre);
//        Book book = new Book("qwe", genre);
//        Book book = new Book("qwe", new Genre().setName("qwe1"));
//        bookService.save(book);
//        System.out.println(book);
        // bookService.insertBook(book);

//        Console.main();
/*
        System.out.println("authors cnt = " + authorService.getAuthorCount());

        List<Book> arrayList = bookService.getAllBooks();
        System.out.println(arrayList.size());
        for (Book book : arrayList) {
            System.out.println(book.toString());
        }

        System.out.println("Book count: " + bookService.getBookCount());

        Book b;
        b = bookService.getBookByName("Voina i mir");
        if (b != null) {
            System.out.println("book '" + b.getName() + "' found!");

            System.out.println("Delete book '" + b.getName() + "'");
            bookService.deleteBook(b);
            System.out.println("Book count: " + bookService.getBookCount());

            System.out.println("Insert book");
            bookService.insertBook(b); //во время вставки генерируется новый id и подменяется в DAO
            System.out.println("Book inserted. id = " + b.getId());
            System.out.println("Book count: " + bookService.getBookCount());

            //Часть 2, снова достать книгу и обновить её

            b = bookService.getBookByName("Voina i mir");

            System.out.println("Update book name from '" + b.getName() + "' to '" + b.getName() + "1'");
            b.setName(b.getName() + 1);
            System.out.println(b.getName());
            bookService.updateBookById(b);

            b = bookService.getBookByName(b.getName());
            System.out.println("book '" + b.getName() + "' found!");
        }*/

}
