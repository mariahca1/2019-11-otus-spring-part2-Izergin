package ru.izergin.hometask.service;

import org.springframework.stereotype.Service;
import ru.izergin.hometask.dao.GenreDao;
import ru.izergin.hometask.domain.Genre;

import java.util.Optional;

@Service
public class GenreServiceImpl {

    private GenreDao genreDao;
    public GenreServiceImpl(GenreDao genreDao){
        this.genreDao = genreDao;
    }

    public Optional<Genre> getByName(String name){return genreDao.getByName(name);}

    public Genre save(Genre genre){return genreDao.save(genre);}
}

