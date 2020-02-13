package ru.izergin.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.dao.BookDao;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.domain.Genre;
import ru.izergin.hometask.dto.BookDto;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private GenreService genreService;
    @Autowired
    private BookDao bookDao;

    @Transactional(readOnly = true)
    public Long getCount() {
        return bookDao.count();
    }

    @Transactional
    //сохранение книги
    public Book save(BookDto bookDto) {
        //Если книга с таким названием уже есть - вернуть ошибку
        if (bookDao.existsByName(bookDto.getName())) {
            throw new DataIntegrityViolationException("Book with name " + bookDto.getName() + " already exists!");
        } else {
            //добавить жанр в справочник, если его нет. если есть - найти
            Genre genre = genreService.save(bookDto.getGenreName());
            return bookDao.save(new Book(bookDto.getName(), genre));
        }
    }

    @Transactional
    //обновление книги
    public Book update(Book book) {
        return bookDao.save(book);
    }

    @Transactional
    public void delete(String name) {
        bookDao.deleteByName(name);
    }

    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookDao.findAll();
    }
}
