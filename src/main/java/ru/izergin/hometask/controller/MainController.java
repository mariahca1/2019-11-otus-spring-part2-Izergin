package ru.izergin.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import ru.izergin.hometask.domain.Author;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.dto.BookDto;
import ru.izergin.hometask.service.AuthorServiceImpl;
import ru.izergin.hometask.service.BookServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private AuthorServiceImpl authorService;

    private final String GENRE_CLASSIC = "Classic";
    private final String GENRE_COMEDY = "Comedy";
    private final String GENRE_COMMON = "Common"; // доступная всем включая анонимуса
    private final String GENRE_SECRET = "Secret"; //доступна только для админа

    private final String BOOK_NAME_1 = "Война и мир";
    private final String BOOK_NAME_2 = "Азбука";


    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            public String getAuthority() {
                return "ROLE_ADMIN";
            }
        };
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        //удалить всё что есть для локальной БД
        authorService.deleteAll();
        bookService.deleteAll();

        //залогиниться под админом для добавления новых книг

        UserDetails user = new User("adm","pass",getAuthorities());
        Authentication auth =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(new BookDto(BOOK_NAME_1, GENRE_CLASSIC, 5)
                .addAuthor(new Author("Иван", "Иванов"))
                .addAuthor(new Author("Петр", "Петров"))
                .addComment("Комментарий 1")
                .addComment("Комментарий 2"));
        bookService.save(bookDtos);
        bookDtos = new ArrayList<>();
        bookDtos.add(new BookDto(BOOK_NAME_2, GENRE_COMEDY, 10)
                .addAuthor(new Author("Сидр", "Сидоров"))
                .addAuthor(new Author("Вертолет", "Вертолетов"))
                .addComment("Комментарий 3")
                .addComment("Комментарий 4"));
        bookService.save(bookDtos);
        bookDtos = new ArrayList<>();
        bookDtos.add(new BookDto("Доступная", GENRE_COMMON, 10)
                .addAuthor(new Author("Сидр1", "Сидоров1"))
                .addAuthor(new Author("Вертолет2", "Вертолетов2"))
                .addComment("Комментарий 5")
                .addComment("Комментарий 6"));
        bookService.save(bookDtos);
        bookDtos = new ArrayList<>();
        bookDtos.add(new BookDto("Секретная", GENRE_SECRET, 12)
                .addAuthor(new Author("Сидр2", "Сидоров2"))
                .addAuthor(new Author("Вертолет3", "Вертолетов3"))
                .addComment("Комментарий 7")
                .addComment("Комментарий 8"));
        bookService.save(bookDtos);

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
