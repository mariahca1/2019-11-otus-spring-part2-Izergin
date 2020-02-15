package ru.izergin.hometask.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.izergin.hometask.domain.Genre;

import java.util.List;

public interface GenreDaoCustom {

    //Вернуть все жанры, начинающиеся на указанную букву и заканчивающиеся на указанную букву
    @Query("select g from Genre g where substring(g.name,1,1) = :startWith and substring(g.name,-1,1) = :endWith")
    List<Genre> findAllBeginAndEndAtCustom(@Param("startWith") String startWith, @Param("endWith") String endWith);

}
