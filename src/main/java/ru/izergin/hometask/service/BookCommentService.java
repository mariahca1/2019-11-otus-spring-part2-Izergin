package ru.izergin.hometask.service;

import org.springframework.stereotype.Service;
import ru.izergin.hometask.dao.BookCommentDao;
import ru.izergin.hometask.domain.BookComment;

@Service
public class BookCommentService {

    private BookCommentDao bookCommentDao;
    public BookCommentService(BookCommentDao bookCommentDao){
        this.bookCommentDao = bookCommentDao;
    }

    public void addComment(BookComment bookComment){
        bookCommentDao.addComment(bookComment);
    }
}
