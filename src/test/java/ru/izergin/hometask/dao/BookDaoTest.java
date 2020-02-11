package ru.izergin.hometask.dao;

import lombok.val;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с книгами")
@DataJpaTest
@Import({BookDao.class,Genre.class}) //из контекста достем этот класс
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) //поднимаем контекст для каждого теста
public class BookDaoTest {

    private final int INITIAL_BOOK_COUNT = 2; //при старте базы в таблице 2 книги

    private final Genre EXISTING_TEST_GENRE1 = new Genre().setId(1L).setName("Classic");
    private final Genre EXISTING_TEST_GENRE2 = new Genre().setId(2L).setName("Horror");
    private final Book EXISTING_TEST_BOOK1 = new Book().setId(1L).setName("Classic book").setGenre(EXISTING_TEST_GENRE1);
    private final Book EXISTING_TEST_BOOK2 = new Book().setId(2L).setName("Horror book").setGenre(EXISTING_TEST_GENRE2);
    private final Book NEW_TEST_BOOK = new Book("New horror book", EXISTING_TEST_GENRE2);

    @Autowired
    private BookDao bookDao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращает верное число книг")
    @Test
    void getBookCountTest() {
        val cnt = bookDao.getCount();
        assertThat(cnt).isEqualTo(INITIAL_BOOK_COUNT);
    }

    @DisplayName("извлекает книгу из базы")
    @Test
    void getBookByNameTest() {
        Book book = bookDao.getByName(EXISTING_TEST_BOOK1.getName()).get();
        assertThat(book.getId()).isEqualTo(EXISTING_TEST_BOOK1.getId());
        assertThat(book.getName()).isEqualTo(EXISTING_TEST_BOOK1.getName());
    }

    @DisplayName("НЕ извлекает не существующую книгу из базы")
    @Test
    void getBookByNameEmptyResultSetTest() {
        String nonExistingBookName = "non existing book name";
        assertThat(bookDao.getByName(nonExistingBookName)).isEmpty();
    }

    @DisplayName("записывает книгу в базу")
    @Test
    void insertNewBookTest() {
        bookDao.save(NEW_TEST_BOOK);
        Book book = bookDao.getByName(NEW_TEST_BOOK.getName()).get();
        assertThat(book.getId()).isEqualTo(NEW_TEST_BOOK.getId());
        assertThat(book.getName()).isEqualTo(NEW_TEST_BOOK.getName());
    }
}
