package ru.izergin.hometask.controller;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.service.AuthorServiceImpl;
import ru.izergin.hometask.service.BookServiceImpl;

import java.util.List;

@Controller
public class MainController {
    private final BookServiceImpl bookService;
    private final AuthorServiceImpl authorService;

    public MainController(BookServiceImpl bookService, AuthorServiceImpl authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {

        System.out.println("Start");
        // Console.main(args);

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
        }
    }
}
