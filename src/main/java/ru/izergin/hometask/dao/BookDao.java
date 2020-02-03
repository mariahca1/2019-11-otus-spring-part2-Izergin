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
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.domain.Genre;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.Objects.isNull;

@Transactional
@Repository
public class BookDao {

    @PersistenceContext
    private EntityManager em;

    public Long getCount() {
        Query query = em.createQuery("select count(b.id) from Book b");
        return (Long) query.getSingleResult();
    }

    public Book save(Book book) {
        if (isNull(book.getId())) {
            em.persist(book);
        } else {
            em.merge(book); //сперва делает select по id, затем update
        }
        em.flush();
        return book;
    }

    public Optional<Book> getByName(String name) {
//        try {
            TypedQuery<Book> typedQuery = em.createQuery("select b from Book b where b.name = :name", Book.class);
            typedQuery.setParameter("name", name);
            List<Book> bookList = typedQuery.getResultList();
            return Optional.ofNullable(bookList.size() == 0 ? null : bookList.get(0));
//        } catch (NoSuchElementException e) {
//            throw new NoSuchElementException("Книга с названием '" + name + "' не найдена!");
//        }
    }

    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void updateById(Long id, Book book) {
        Query query = em.createQuery("update Book b set b.name = :name, b.genre = :genre , b.comments = :comments where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", book.getName());
        query.setParameter("genre", book.getGenre());
        query.setParameter("comments", book.getComments());
        query.executeUpdate();
    }

    public void updateNameById(Long id, Book book) {
        Query query = em.createQuery("update Book b set b.name = :name where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", book.getName());
        query.executeUpdate();
    }
    public void updateGenreById(Long id, Book book) {
        Query query = em.createQuery("update Book b set b.genre = :genre where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("genre", book.getGenre());
        query.executeUpdate();
    }

    public List<Book> getAll() {
        Query query = em.createQuery("select b from Book b");
        return query.getResultList();
    }
}
