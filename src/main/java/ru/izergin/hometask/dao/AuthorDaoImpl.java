package ru.izergin.hometask.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorDaoImpl {

//    private final NamedParameterJdbcOperations jdbcTemplate;

    //настройки для postgre базы данных
//    public AuthorDaoImpl(@Qualifier("namedParameterJdbcTemplatePg") NamedParameterJdbcOperations jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

//    public AuthorDaoImpl(/*@Qualifier("namedParameterJdbcTemplateH2") */NamedParameterJdbcOperations jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public int getAuthorCount() {
//        Map<String, Integer> params = new HashMap<>();
//        return jdbcTemplate.queryForObject("select count(1) from authors", params, Integer.class);
//    }
}
