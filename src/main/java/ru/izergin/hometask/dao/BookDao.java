package ru.izergin.hometask.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.domain.Book;

import javax.persistence.*;
import java.util.*;

import static java.util.Objects.isNull;

@Repository
public class BookDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(propagation = Propagation.REQUIRED)
    public Long getCount() {
        Query query = em.createQuery("select count(b.id) from Book b");
        return (Long) query.getSingleResult();
    }

    public Optional<Book> getByName(String name) {
        TypedQuery<Book> typedQuery = em.createQuery("select b from Book b where b.name = :name", Book.class);
        typedQuery.setParameter("name", name);
        List<Book> bookList = typedQuery.getResultList();
        return Optional.ofNullable(bookList.size() == 0 ? null : bookList.get(0));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Book save(Book book) {
        if (isNull(book.getId())) {
            //если нет id, то это может быть новая книга или уже имеющаяся в базе
            Optional<Book> optionalBook = getByName(book.getName());
            if (!optionalBook.isPresent()) {
                em.persist(book);
                return book;
            } else {
                //если в базе уже есть книга, обновим её поля, предварительно присвоив ID (по нему мерж)
                book.setId(optionalBook.get().getId());
                return em.merge(book);
            }
        } else {
            em.merge(book);
        }
        return book;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(Book book) {
        em.remove(book);
    }

    public List<Book> getAll() {
        Query query = em.createQuery("select b from Book b");
        return query.getResultList();
    }
}
