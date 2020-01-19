package ru.izergin.hometask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.service.AuthorServiceImpl;
import ru.izergin.hometask.service.BookServiceImpl;

import java.util.List;

@SpringBootApplication
public class HometaskApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(HometaskApplication.class, args);
        System.out.println("Start");
        // Console.main(args);

        BookServiceImpl bookService = context.getBean(BookServiceImpl.class);
        AuthorServiceImpl authorService = context.getBean(AuthorServiceImpl.class);

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
