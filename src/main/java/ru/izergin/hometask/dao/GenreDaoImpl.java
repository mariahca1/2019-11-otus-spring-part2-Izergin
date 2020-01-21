package ru.izergin.hometask.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GenreDaoImpl {

    private final NamedParameterJdbcOperations jdbcTemplate;

    public GenreDaoImpl(@Qualifier("namedParameterJdbcTemplateH2") NamedParameterJdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getGenreCount() {
        Map<String, Integer> params = new HashMap<>();
        return jdbcTemplate.queryForObject("select count(1) from genres", params, Integer.class);
    }


}
