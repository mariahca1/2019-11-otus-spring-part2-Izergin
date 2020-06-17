package ru.izergin.hometask.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.dto.BookIdRequestDto;
import ru.izergin.hometask.repository.AuthorReactiveRepository;
import ru.izergin.hometask.repository.BookReactiveRepository;

@WebFluxTest
class BookControllerTest {
    @MockBean
    BookReactiveRepository bookReactiveRepository;
    @MockBean
    AuthorReactiveRepository authorReactiveRepository;

    private final Book TEST_BOOK = new Book("book1", "Classic", 10);

    @Autowired
    WebTestClient client;

    @Test
    void getAllBookTest() {

        Flux<Book> bookFlux = Flux.just(TEST_BOOK);
        Mockito.when(bookReactiveRepository.findAll()).thenReturn(bookFlux);

        client.get()
                .uri("/api/findAll")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class);
    }

    @Test
    void findByIdTest() {

        Mono<Book> bookMono = Mono.just(TEST_BOOK);
        Mockito.when(bookReactiveRepository.findById(Mockito.any(String.class))).thenReturn(bookMono);

        client.post()
                .uri("/api/findById", 1)
                .body(BodyInserters.fromObject(new BookIdRequestDto("q")))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Book.class);
    }
}