package ru.izergin.hometask.service;

import org.springframework.stereotype.Service;
import ru.izergin.hometask.dao.BookDao;
import ru.izergin.hometask.domain.Book;

@Service
public class BookServiceImpl {

    private BookDao bookDao;
    public BookServiceImpl(BookDao bookDao){
        this.bookDao = bookDao;
    }

//    public int getBookCount(){
//        return bookDao.getBookCount();
//    }
//
//    public Book getBookByName(String name) {return bookDao.getBookByName(name);}
//
//    public void deleteBook(Book book) {bookDao.deleteBook(book);}
//    public void updateBookById(Book book) {bookDao.updateBookById(book);}
//
//    public void insertBook(Book book){bookDao.insertBook(book);}
//
//    public List<Book> getAllBooks(){return bookDao.getAllBooks();}


    //em
    public Book save(Book book){return bookDao.save(book);};
}
