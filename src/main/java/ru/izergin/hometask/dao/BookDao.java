package ru.izergin.hometask.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.domain.Book;

import javax.persistence.*;
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

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    public Book save(Book book) {
        if (isNull(book.getId())) {
            em.persist(book);
        } else {
            em.merge(book);
        }
        return book;
    }

    public Optional<Book> getByName(String name) {
        TypedQuery<Book> typedQuery = em.createQuery("select b from Book b where b.name = :name", Book.class);
        typedQuery.setParameter("name", name);
        List<Book> bookList = typedQuery.getResultList();
        return Optional.ofNullable(bookList.size() == 0 ? null : bookList.get(0));
    }

    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public List<Book> getAll() {
        Query query = em.createQuery("select b from Book b");
        return query.getResultList();
    }
}
