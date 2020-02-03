package ru.izergin.hometask.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.izergin.hometask.domain.Genre;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Transactional
@Repository
public class GenreDao {

    @PersistenceContext
    private EntityManager em;

    public Optional<Genre> getByName(String name){
        TypedQuery<Genre> typedQuery = em.createQuery("select g from Genre g where g.name = :name", Genre.class);
        typedQuery.setParameter("name",name);
        List<Genre> genreList = typedQuery.getResultList();
        return Optional.ofNullable(genreList.size() == 0 ? null : genreList.get(0));
    }

    public Genre save(Genre genre) {
        if (isNull(genre.getId())) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }
}
