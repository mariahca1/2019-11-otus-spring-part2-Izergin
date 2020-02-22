package ru.izergin.hometask.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.dao.BookDao;
import ru.izergin.hometask.domain.Author;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.dto.BookDto;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorServiceImpl authorService;

    @Transactional(readOnly = true)
    public Long getCount() {
        return bookDao.count();
    }

    @SneakyThrows
    @Transactional
    //сохранение книги
    public Book save(BookDto bookDto) {
        List<Author> authorList = authorService.saveList(bookDto.getAuthors());
        return bookDao.save(new Book(bookDto.getName(), bookDto.getGenreName(), bookDto.getPageCount()).setAuthors(authorList));
    }

    @SneakyThrows
    @Transactional
    //обновление книги
    public Book update(Book book) {
        List<Author> authorList = authorService.saveList(book.getAuthors());
        return bookDao.save(book).setAuthors(authorList);
    }

    @Transactional
    public void deleteAll() {
        bookDao.deleteAll();
    }

    @Transactional
    public void deleteByName(String name) {
        bookDao.deleteByName(name);
    }

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
