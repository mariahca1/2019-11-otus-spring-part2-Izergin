package ru.izergin.hometask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.izergin.hometask.domain.Genre;

import java.util.List;

public interface GenreDao extends JpaRepository<Genre, Long>, GenreDaoCustom {
    List<Genre> findByName(String name);
}
