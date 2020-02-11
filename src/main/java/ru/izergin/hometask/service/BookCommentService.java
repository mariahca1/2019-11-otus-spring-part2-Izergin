package ru.izergin.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.dao.BookCommentDao;

@Service
@Transactional
public class BookCommentService {

    @Autowired
    private BookCommentDao bookCommentDao;

}
