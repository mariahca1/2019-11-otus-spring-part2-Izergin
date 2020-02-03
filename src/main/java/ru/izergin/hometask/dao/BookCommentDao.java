package ru.izergin.hometask.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.domain.BookComment;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class BookCommentDao {

    @PersistenceContext
    private EntityManager em;

    public void addComment(BookComment bookComment){
        em.persist(bookComment);
    }


}
