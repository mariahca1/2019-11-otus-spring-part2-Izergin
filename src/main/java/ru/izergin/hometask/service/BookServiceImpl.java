package ru.izergin.hometask.service;

import org.springframework.stereotype.Service;
import ru.izergin.hometask.dao.BookDaoImpl;
import ru.izergin.hometask.domain.Book;

import java.util.List;

@Service
public class BookServiceImpl {

    private BookDaoImpl bookDao;
    public BookServiceImpl(BookDaoImpl bookDao){
        this.bookDao = bookDao;
    }

    public int getBookCount(){
        return bookDao.getBookCount();
    }

    public Book getBookByName(String name) {return bookDao.getBookByName(name);}

    public void deleteBook(Book book) {bookDao.deleteBook(book);}
    public void updateBookById(Book book) {bookDao.updateBookById(book);}

    public void insertBook(Book book){bookDao.insertBook(book);}

    public List<Book> getAllBooks(){return bookDao.getAllBooks();}
}
