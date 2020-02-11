package ru.izergin.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.dao.GenreDao;
import ru.izergin.hometask.domain.Genre;

import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreDao genreDao;

    @Transactional(readOnly = true)
    public Optional<Genre> getByName(String name){return genreDao.getByName(name);}

    @Transactional
    public Genre save(String name){return genreDao.save(name);}
}

