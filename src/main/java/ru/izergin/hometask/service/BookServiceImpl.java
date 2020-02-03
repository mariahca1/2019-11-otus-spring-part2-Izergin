package ru.izergin.hometask.service;

import org.springframework.stereotype.Service;
import ru.izergin.hometask.dao.BookDao;
import ru.izergin.hometask.domain.Book;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl {

    private BookDao bookDao;
    public BookServiceImpl(BookDao bookDao){
        this.bookDao = bookDao;
    }

    public Long getCount(){return bookDao.getCount();}

    public Book save(Book book){return bookDao.save(book);};

    public Optional<Book> getBookByName(String name) {return bookDao.getByName(name);}

    public void deleteById(Long id) {bookDao.deleteById(id);}

    public void updateById(Long id, Book book){bookDao.updateById(id,book);}
    public void updateNameById(Long id, Book book){bookDao.updateNameById(id,book);}
    public void updateGenreById(Long id, Book book){bookDao.updateGenreById(id,book);}

    public List<Book> getAll(){return bookDao.getAll();}
}
