package ru.izergin.hometask.service;

import org.springframework.stereotype.Service;
import ru.izergin.hometask.dao.BookDaoImpl;

@Service
public class BookServiceImpl {

    private BookDaoImpl bookDao;
    public BookServiceImpl(BookDaoImpl bookDao){
        this.bookDao = bookDao;
    }

    public int getbookCount(){
        return bookDao.getBookCount();
    }
}
