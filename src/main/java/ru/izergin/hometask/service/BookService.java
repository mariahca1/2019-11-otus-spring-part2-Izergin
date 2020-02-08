package ru.izergin.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.dao.BookDao;
import ru.izergin.hometask.dao.GenreDao;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.domain.Genre;
import ru.izergin.hometask.dto.BookDto;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private GenreDao genreDao;
    @Autowired
    private BookDao bookDao;

    @Transactional(readOnly = true)
    public Long getCount() {
        return bookDao.getCount();
    }

    @Transactional
    //сохранение книги
    public Book save(BookDto bookDto) {
        //достать жанр, если такого еще нет в базе - то сохранить
        Genre genre2 = genreDao.save(bookDto.getGenre());
        Book book = new Book(bookDto.getName(), genre2);
        return bookDao.save(book);
    }

    @Transactional
    //обновление книги
    public Book update(Book book) {
        return bookDao.save(book);
    }

    @Transactional
    public void delete(String name) {
        //проверить, есть ли такая книга в базе? Если нет то и удалять нечего
        Optional<Book> optionalBook = bookDao.getByName(name);
        if (optionalBook.isPresent()) {
            bookDao.delete(optionalBook.get());
        }
    }

    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookDao.getAll();
    }

}
