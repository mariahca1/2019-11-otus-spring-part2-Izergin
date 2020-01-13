package ru.izergin.hometask.service;

import org.springframework.stereotype.Service;
import ru.izergin.hometask.dao.GenreDaoImpl;

@Service
public class GenreServiceImpl {

    private GenreDaoImpl genreDao;
    public GenreServiceImpl(GenreDaoImpl genreDao){
        this.genreDao = genreDao;
    }

    public int getGenreCount(){
        return genreDao.getGenreCount();
    }
}
