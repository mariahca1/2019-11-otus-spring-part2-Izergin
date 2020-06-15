package ru.izergin.hometask.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.test.StepVerifier;
import ru.izergin.hometask.domain.Book;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Dao для работы с книгами")
@DataMongoTest

//поднимает контекст для каждог теста + свежую БД
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryTest {

    @Autowired
    BookReactiveRepository bookReactiveRepository;

    private final Book TEST_BOOK = new Book("book1", "Classic", 10);
    private final Book TEST_BOOK2 = new Book("book2", "Classic", 10);

    @Test
    public void saveBookTest() {
        StepVerifier
                .create(bookReactiveRepository.save(TEST_BOOK))
                .assertNext(book -> {
                    assertEquals(book.getId(), TEST_BOOK.getId());
                    assertEquals(book.getName(), TEST_BOOK.getName());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void findBookTest() {
        bookReactiveRepository.save(TEST_BOOK).subscribe();
        bookReactiveRepository.save(TEST_BOOK2).subscribe();

        StepVerifier
                .create(bookReactiveRepository.findAll())
                .expectNextCount(1)
                .assertNext(book -> {
                    assertEquals(book.getGenre(), TEST_BOOK.getGenre());
                })
                .expectComplete()
                .verify();
    }
}
