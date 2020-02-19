package ru.izergin.hometask.dao;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.izergin.hometask.domain.Author;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.service.AuthorServiceImpl;
import ru.izergin.hometask.service.BookServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с книгами")
@DataMongoTest

@ExtendWith(SpringExtension.class)
@Import({BookServiceImpl.class, AuthorServiceImpl.class})
public class BookDaoTest {

    @Autowired
    BookServiceImpl bookService;
    @Autowired
    AuthorServiceImpl authorService;

    private final Book EXISTING_TEST_BOOK1 = new Book("book1", "Classic", 10);

    @BeforeEach
    public  void init(@Autowired MongoTemplate mongoTemplate){
        authorService.deleteAll();
        bookService.deleteAll();
        Author author = new Author("qwe", "qwe1");
        mongoTemplate.insert(author,"authors");
        mongoTemplate.insert(EXISTING_TEST_BOOK1,"books");
    }

    @DisplayName("возвращает верное число авторов")
    @Test
    public void getBookCountTest(@Autowired MongoTemplate mongoTemplate) {
        assertThat(mongoTemplate.count(new Query(), "authors")).isEqualTo(1L);
    }

    @DisplayName("извлекает книги по заданному жанру")
    @Test
    void getBookByGenreTest() {
        List<Book> bookList= bookService.findByGenre("Classic");
        assertThat(bookList.size()).isEqualTo(1);
        assertThat(bookList.get(0).getName()).isEqualTo(EXISTING_TEST_BOOK1.getName());
        assertThat(bookList.get(0).getGenre()).isEqualTo(EXISTING_TEST_BOOK1.getGenre());
    }
}
