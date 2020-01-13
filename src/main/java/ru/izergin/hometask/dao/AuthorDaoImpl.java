package ru.izergin.hometask.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorDaoImpl {

    private final NamedParameterJdbcOperations jdbcTemplate;

    public AuthorDaoImpl(NamedParameterJdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getAuthorCount() {
        Map<String, Integer> params = new HashMap<>();
        return jdbcTemplate.queryForObject("select count(1) from authors", params, Integer.class);
    }
}
