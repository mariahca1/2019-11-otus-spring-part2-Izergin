package ru.izergin.hometask.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.izergin.hometask.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl {

    private final NamedParameterJdbcOperations jdbcTemplate;

    public BookDaoImpl(@Qualifier("namedParameterJdbcTemplateH2") NamedParameterJdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getBookCount() {
        Map<String, Integer> params = new HashMap<>();
        int result;
        try {
            result = jdbcTemplate.queryForObject("select count(1) from books", params, Integer.class);
            //Unboxing of 'jdbcTemplate.queryForObject("select count(1) from books", params, Integer.class)' may produce 'NullPointerException'
            //Как бороться с этой ошибкой?
        } catch (NullPointerException e1) {
            result = -1;
        }
        return result;
    }

    public Book getBookByName(String name) throws EmptyResultDataAccessException {
        Map<String, Object> params = new HashMap<>(1);
        params.put("name", name);
        Book b;
        try {
            b = jdbcTemplate.queryForObject("select id,name,author_id,genre_id from books where name = :name", params, new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("Книга с названием '" + name + "' не найдена!", 1);
        }
        return b;
    }

    public void insertBook(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("author_id", book.getAuthorId());
        params.addValue("genre_id", book.getGenreId());
        KeyHolder kh = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update("insert into books (name,author_id,genre_id) values (:name,:author_id,:genre_id)", params, kh);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("Книга с таким названием уже существует!");
        }
        book.setId((int) kh.getKeyList().get(0).get("id"));
    }

    public void deleteBook(Book book) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("name", book.getName());
        jdbcTemplate.update("delete from books where name = :name", params);
    }

    //обновление всех полей книги по её ID. Пока без особого смысла (идентификаторы автора пользователю все равно не известны)
    public void updateBookById(Book book) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("author_id", book.getAuthorId());
        params.put("genre_id", book.getGenreId());
        jdbcTemplate.update("update books set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id", params);
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("select * from books", BeanPropertyRowMapper.newInstance(Book.class));
    }

    public static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int authorId = resultSet.getInt("author_id");
            int genreId = resultSet.getInt("genre_id");
            return new Book(id, name, authorId, genreId);
        }
    }
}
