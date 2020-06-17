package ru.izergin.hometask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.izergin.hometask.service.BookServiceImpl;

@SpringBootApplication
public class Application {
    private static BookServiceImpl bookService;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        bookService = context.getBean(BookServiceImpl.class);

        bookService.init();
    }

}
