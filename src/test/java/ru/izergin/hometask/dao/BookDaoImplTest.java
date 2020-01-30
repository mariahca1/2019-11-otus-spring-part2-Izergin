//package ru.izergin.hometask.dao;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import ru.izergin.hometask.config.H2Config;
//import ru.izergin.hometask.domain.Book;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DisplayName("Dao для работы с книгами")
//@JdbcTest
//@Import({BookDaoImpl.class}) //из контекста достем этот класс
//@ContextConfiguration(classes = {H2Config.class}) //подтянуть конфиг (бины) из данного класса
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) //поднимаем контекст для каждого теста
//public class BookDaoImplTest {
//
//    private final int INITIAL_BOOK_COUNT = 2; //при старте базы в таблице 2 книги
//
//    private final Book EXISTING_TEST_BOOK1 = new Book(1, "Voina i mir", 1, 1);
//    private final Book EXISTING_TEST_BOOK2 = new Book(2, "kniga 2", 2, 2);
//    private final Book NEW_TEST_BOOK = new Book(1, "test book", 1, 1);
//
//    @Autowired
//    public BookDaoImpl bookDao;
//
//    @DisplayName("возвращает верное число книг")
//    @Test
//    @Order(1)
//    void getBookCountTest() {
//        assertThat(bookDao.getBookCount()).isEqualTo(INITIAL_BOOK_COUNT);
//    }
//
//    @DisplayName("извлекает книгу из базы")
//    @Test
//    @Order(2)
//    void getBookByNameTest() {
//        Book book = bookDao.getBookByName(EXISTING_TEST_BOOK1.getName());
//        assertThat(book.getId()).isEqualTo(EXISTING_TEST_BOOK1.getId());
//        assertThat(book.getName()).isEqualTo(EXISTING_TEST_BOOK1.getName());
//        assertThat(book.getAuthorId()).isEqualTo(EXISTING_TEST_BOOK1.getAuthorId());
//        assertThat(book.getGenreId()).isEqualTo(EXISTING_TEST_BOOK1.getGenreId());
//    }
//
//    @DisplayName("НЕ извлекает не существующую книгу из базы")
//    @Test
//    @Order(3)
//    void getBookByNameEmptyResultSetTest() {
//        String nonExistingBookName = "non existing book name";
//        Throwable throwable = assertThrows(EmptyResultDataAccessException.class, () -> bookDao.getBookByName(nonExistingBookName));
//        assertThat(throwable.getMessage()).isEqualTo("Книга с названием '" + nonExistingBookName + "' не найдена!");
//    }
//
//    @DisplayName("записывает книгу в базу")
//    @Test
//    @Order(4)
//    void insertNewBookTest() {
//        bookDao.insertBook(NEW_TEST_BOOK);
//        Book book = bookDao.getBookByName(NEW_TEST_BOOK.getName());
//        assertThat(book.getId()).isEqualTo(NEW_TEST_BOOK.getId());
//        assertThat(book.getName()).isEqualTo(NEW_TEST_BOOK.getName());
//        assertThat(book.getAuthorId()).isEqualTo(NEW_TEST_BOOK.getAuthorId());
//        assertThat(book.getGenreId()).isEqualTo(NEW_TEST_BOOK.getGenreId());
//    }
//
//    @DisplayName("Не записывает книгу в базу повторно")
//    @Test
//    @Order(5)
//    void insertExistingBookTest() {
//        bookDao.insertBook(NEW_TEST_BOOK);
//        Throwable throwable = assertThrows(DuplicateKeyException.class, () -> bookDao.insertBook(NEW_TEST_BOOK));
//        assertThat(throwable.getMessage()).isEqualTo("Книга с таким названием уже существует!");
//    }
//
//    @DisplayName("возвращает список всех книг")
//    @Test
//    @Order(6)
//    void getAllBooksTest() {
//        List<Book> bookList = bookDao.getAllBooks();
//        assertThat(bookList.get(0)).isEqualToComparingFieldByField(EXISTING_TEST_BOOK1);
//        assertThat(bookList.get(1)).isEqualToComparingFieldByField(EXISTING_TEST_BOOK2);
//        assertThat(bookList.size()).isEqualTo(INITIAL_BOOK_COUNT);
//    }
//
//    @DisplayName("удаляет книгу")
//    @Test
//    @Order(7)
//    void deleteBookTest() {
//        bookDao.deleteBook(EXISTING_TEST_BOOK1);
//        List<Book> bookList = bookDao.getAllBooks();
//        assertThat(bookList.get(0)).isEqualToComparingFieldByField(EXISTING_TEST_BOOK2);
//        assertThat(bookList.size()).isEqualTo(1);
//    }
//
//    @DisplayName("обновляет данные о книге")
//    @Test
//    @Order(8)
//    void updateBookByIdTest() {
//        String newBookName = "new book name";
//        Integer newBookAuthorId = 123;
//        Integer newBookGenreId = 456;
//        Book book = bookDao.getBookByName(EXISTING_TEST_BOOK1.getName());
//        book.setName(newBookName).setAuthorId(newBookAuthorId).setGenreId(newBookGenreId);
//        bookDao.updateBookById(book);
//
//        List<Book> bookList = bookDao.getAllBooks();
//        assertThat(bookList.get(0)).isEqualToComparingFieldByField(book);
//    }
//}
