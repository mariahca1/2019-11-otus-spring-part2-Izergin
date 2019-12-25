package ru.izergin.hometask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.izergin.hometask.service.BookServiceImpl;

@SpringBootApplication
public class HometaskApplication {

	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(HometaskApplication.class, args);
		System.out.println("qwe");
		BookServiceImpl bookService = context.getBean(BookServiceImpl.class);
		System.out.println(bookService.getbookCount());


	}

}
