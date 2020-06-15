package ru.izergin.hometask.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.izergin.hometask.domain.Author;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.dto.BookAuthorDeleteRequestDto;
import ru.izergin.hometask.dto.BookIdRequestDto;
import ru.izergin.hometask.repository.AuthorReactiveRepository;
import ru.izergin.hometask.repository.BookReactiveRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private final BookReactiveRepository bookReactiveRepository;
    private final AuthorReactiveRepository authorReactiveRepository;

    @GetMapping("/api/findAll")
    public Flux<Book> findAll() {
        return bookReactiveRepository.findAll();
    }

    @PostMapping("/api/findById")
    public Mono<Book> findById(@RequestBody BookIdRequestDto bookIdRequestDto) {
        return bookReactiveRepository.findById(bookIdRequestDto.getId());
    }

    @PostMapping(value = "/api/create")
    public Mono<ResponseEntity<Book>> create(@RequestBody Book book) {
        // сперва проверяем, есть ли книга с таким названием, затем сохраняем авторов и затем - книгу
        return bookReactiveRepository.findByName(book.getName())
                .map((x) -> ResponseEntity.status(HttpStatus.CONFLICT.value()).body(book)) //если книга найдена - ошибка
                .switchIfEmpty(authorReactiveRepository.saveAll(book.getAuthors())
                        .last() //только последнего автора. Для трансформации Flux->Mono
                        .flatMap((q) -> bookReactiveRepository.save(book).map(b -> ResponseEntity.ok(b))));
    }

    @PostMapping(value = "/api/deleteById")
    public Mono<ResponseEntity<Void>> deleteById(@RequestBody Book b) {
        return bookReactiveRepository.findById(b.getId())
                .flatMap(book -> bookReactiveRepository.delete(book)
                        .then(Mono.just(ResponseEntity.ok().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/api/book/update")
    public Mono<ResponseEntity<Book>> updateBook(@RequestBody Book book) {
        return bookReactiveRepository.save(book).map(b -> ResponseEntity.ok(b))
                .onErrorReturn(ResponseEntity.badRequest().body(book));
    }

    @PostMapping(value = "/api/author/delete")
    public Mono<ResponseEntity<Book>> AuthorDelete(@RequestBody BookAuthorDeleteRequestDto bookAuthorDeleteRequestDto) {
        Mono<Book> bookMono = bookReactiveRepository.findById(bookAuthorDeleteRequestDto.getBookId());
        return bookMono.flatMap(book -> {
            List<Author> authors = book.getAuthors();
            authors.remove(bookAuthorDeleteRequestDto.getAuthorNum());
            return bookReactiveRepository.save(book.setAuthors(authors)).map(b -> ResponseEntity.ok(b));
        });
    }
}

