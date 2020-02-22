package ru.izergin.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.izergin.hometask.dao.AuthorDao;
import ru.izergin.hometask.domain.Author;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorDao authorDao;

    public Author save(Author author) {
        return authorDao.save(author);
    }

    public List<Author> saveList(List<Author> authorList) {
        List<Author> res = new ArrayList<>();
        if (authorList.size() != 0) {
            for (Author author : authorList) {
                res.add(authorDao.save(author));
            }
        }
        return res;
    }

    public void deleteAll(){
        authorDao.deleteAll();
    }
}
