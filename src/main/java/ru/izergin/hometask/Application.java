package ru.izergin.hometask;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.repository.BookReactiveRepository;
import ru.izergin.hometask.service.BookService;
import ru.izergin.hometask.service.BookServiceImpl;

import javax.naming.Context;

@SpringBootApplication
//@EnableMongoRepositories
//@EnableReactiveMongoRepositories
//@AllArgsConstructor
public class Application {
//    @Autowired
    private static BookServiceImpl bookService;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

//        bookService.save()
//        System.out.println("q");
//        Flux<Book> bookFlux = bookReactiveRepository
//                .findAll()
//                .doOnNext(System.out::println);
        bookService = context.getBean(BookServiceImpl.class);
//        bookService.test();

        bookService.test1();
    }

}
