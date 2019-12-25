package ru.izergin.hometask.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookDaoImpl {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BookDaoImpl(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getBookCount(){
        Map<String,Integer> m = new HashMap<>();
        return jdbcTemplate.queryForObject("select count(1) from java_schema_izergin.books",m,Integer.class);
    }
}
