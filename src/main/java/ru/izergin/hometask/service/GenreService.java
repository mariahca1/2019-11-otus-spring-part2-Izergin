package ru.izergin.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.dao.GenreDao;
import ru.izergin.hometask.domain.Genre;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreDao genreDao;

    @Transactional(readOnly = true)
    public Genre getByName(String name) {
        List<Genre> genreList = genreDao.findByName(name);//если ничего не найдено вернется инициализированный но пустой массив
        return genreList.size() == 0 ? null : genreList.get(0);
    }

    @Transactional
    public Genre save(String name) {
        Genre genre = getByName(name); //если пытаемся вставить жанр который уже есть - просто вернем существующий
        if (genre == null) {
            return genreDao.save(new Genre(name));
        } else {
            return genre;
        }
    }

    public List<Genre> findAllBeginAndEndAtCustom(String startWith, String endWith) {
        return genreDao.findAllBeginAndEndAtCustom(startWith, endWith);
    }
}

