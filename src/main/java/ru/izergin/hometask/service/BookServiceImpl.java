package ru.izergin.hometask.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.dao.BookDao;
import ru.izergin.hometask.domain.Author;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.dto.BookDto;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookDao bookDao;
    private AuthorServiceImpl authorService;

    @Transactional(readOnly = true)
    public Long getCount() {
        return bookDao.count();
    }

    public Book findById(String id) {
        return bookDao.findById(id);
    }

    @SneakyThrows
    @Transactional
    @PreFilter(value = "hasRole('ROLE_ADMIN') or !'Secret'.equals(filterObject.getGenreName())",
            filterTarget = "bookDtos")
    //сохранение книги
    public Book save(List<BookDto> bookDtos) {
        BookDto bookDto;
        try {
            bookDto = bookDtos.get(0); // вот тут костыль
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalAccessException("Данное действие вам запрещено");
        }
        List<Author> authorList = authorService.saveList(bookDto.getAuthors());
        Book book = new Book(bookDto.getName(), bookDto.getGenreName(), bookDto.getPageCount())
                .setAuthors(authorList)
                .setComments(bookDto.getComments());
        return bookDao.save(book);
    }

    @SneakyThrows
    @Transactional
    //обновление книги
    public Book update(Book book) {
        List<Author> authorList = authorService.saveList(book.getAuthors());
        return bookDao.save(book).setAuthors(authorList);
    }

    @Transactional
    public Book deleteComment(String bookId, int commentNum) {
        Book book = bookDao.findById(bookId);
        book.getComments().remove(commentNum);
        return bookDao.save(book);
    }

    @Transactional
    public Book addComment(String bookId, String commentText) {
        Book book = bookDao.findById(bookId);
        book.addComment(commentText);
        return update(book);
    }

    @Transactional
    public Book deleteAuthor(String bookId, int authorNum) {
        Book book = bookDao.findById(bookId);
        book.getAuthors().remove(authorNum);
        return update(book);
    }

    @Transactional
    public Book addAuthor(String bookId, Author author) {
        Book book = bookDao.findById(bookId).addAuthor(author);
        return update(book);
    }

    @Transactional
    public void deleteAll() {
        bookDao.deleteAll();
    }

    @Transactional
    public void deleteByName(String name) {
        bookDao.deleteByName(name);
    }

    @Transactional
    public void deleteById(String id) {
        bookDao.deleteById(id);
    }

    @PostFilter("isAnonymous() and 'Common'.equals(filterObject.getGenre()) " +
            "or hasRole('ROLE_ADMIN') " +
            "or !hasRole('ROLE_ADMIN') and !isAnonymous() and !'Secret'.equals(filterObject.getGenre())")
    public List<Book> getAll() {
        return bookDao.findAll();
    }

    public List<Book> findByGenre(String genre) {
        return bookDao.findByGenre(genre);
    }

    public List<Book> findByGenreFirstLetter(String letter) {
        return bookDao.findByGenreFirstLetter(letter);
    }
}
