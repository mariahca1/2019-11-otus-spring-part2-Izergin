package ru.izergin.hometask.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.dto.BookAuthorDeleteRequestDto;
import ru.izergin.hometask.dto.BookDto;
import ru.izergin.hometask.dto.BookIdRequestDto;
import ru.izergin.hometask.service.BookServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

    @RequestMapping("/test")
    public @ResponseBody String test() {
        return "hello";
    }

    @GetMapping("/api/getAllBooks")
    public List<Book> getAllBooks() {
        List<Book> bookList = bookService.getAll();
        return bookList;
    }

    @PostMapping("/api/getBook")
    public Book getBook(@RequestBody BookIdRequestDto bookIdRequestDto) {
        System.out.println(bookIdRequestDto.getId());
        Book book =  bookService.findById(bookIdRequestDto.getId());
        return book;
    }

    @PostMapping(value = "/api/createBook", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> postCreateBook(@RequestBody Book book) {
        try {
            bookService.save(new BookDto(book.getName(), book.getGenre(), book.getPageCount()));
            int result_code = HttpStatus.OK.value();
            String result_message = "Книга создана";
            return ResponseEntity.status(HttpStatus.OK).body("{\"result_code\":\"" + result_code + "\"," +
                    "\"result_message\":\"" + result_message + "\"}");
        } catch (DataIntegrityViolationException e) {
            int result_code = HttpStatus.CONFLICT.value();
            String result_message = "Книга с таким именем уже существует";
            return ResponseEntity.status(HttpStatus.OK).body("{\"result_code\":\"" + result_code + "\"," +
                    "\"result_message\":\"" + result_message + "\"}");
        }
    }

    @PostMapping(value = "/api/deleteBook")
    @ResponseBody
    public ResponseEntity<String> postDeleteBook(@RequestBody BookIdRequestDto bookIdRequestDto) {
        bookService.deleteById(bookIdRequestDto.getId());
        int result_code = HttpStatus.OK.value();
        String result_message = "Книга успешно удалена";
        return ResponseEntity.status(HttpStatus.OK).body("{\"result_code\":\"" + result_code + "\"," +
                "\"result_message\":\"" + result_message + "\"}");
    }

    @PostMapping(value = "/api/updateBook", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> postUpdateBook(@RequestBody Book book) {
        try {
            bookService.update(book);
            int result_code = HttpStatus.OK.value();
            String result_message = "Книга обновлена";
            return ResponseEntity.status(HttpStatus.OK).body("{\"result_code\":\"" + result_code + "\"," +
                    "\"result_message\":\"" + result_message + "\"}");
        } catch (DataIntegrityViolationException e) {
            int result_code = HttpStatus.CONFLICT.value();
            String result_message = "Книга с таким именем уже существует";
            return ResponseEntity.status(HttpStatus.OK).body("{\"result_code\":\"" + result_code + "\"," +
                    "\"result_message\":\"" + result_message + "\"}");
        }
    }

    @PostMapping(value="/api/deleteAuthor", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getDeleteAuthor(@RequestBody BookAuthorDeleteRequestDto bookAuthorDeleteRequestDto) {
        System.out.println(bookAuthorDeleteRequestDto);
        bookService.deleteAuthor(bookAuthorDeleteRequestDto.getBookId(), bookAuthorDeleteRequestDto.getAuthorNum());
        int result_code = HttpStatus.OK.value();
        String result_message = "Автор удален";
        return ResponseEntity.status(HttpStatus.OK).body("{\"result_code\":\"" + result_code + "\"," +
                "\"result_message\":\"" + result_message + "\"}");
    }
}

