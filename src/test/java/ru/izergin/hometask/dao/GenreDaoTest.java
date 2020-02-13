package ru.izergin.hometask.dao;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import ru.izergin.hometask.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами")
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) //поднимаем контекст для каждого теста
public class GenreDaoTest {

    private final int INITIAL_BOOK_COUNT = 2; //при старте базы в таблице 2 книги

    private final Genre EXISTING_TEST_GENRE1 = new Genre().setId(1L).setName("Classic");
    private final Genre EXISTING_TEST_GENRE2 = new Genre().setId(2L).setName("Horror");

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращает верное число жанров")
    @Test
    void getGenreCountTest() {
        val cnt = genreDao.count();
        assertThat(cnt).isEqualTo(INITIAL_BOOK_COUNT);
    }

    @DisplayName("возвращает корректные данные кастомным методом")
    @Test
    void findAllBeginAndEndAtCustomTest() {
        List<Genre> genreList = genreDao.findAllBeginAndEndAtCustom("C","c");
        assertThat(genreList.size()).isEqualTo(1);
        assertThat(genreList.get(0).toString()).isEqualTo(EXISTING_TEST_GENRE1.toString());
    }

    @DisplayName("возвращает корректные данные кастомным методом 2")
    @Test
    void findAllBeginAndEndAtCustomTest2() {
        em.persist(new Genre("C123c"));
        List<Genre> genreList = genreDao.findAllBeginAndEndAtCustom("C","c");
        assertThat(genreList.size()).isEqualTo(2);
    }
}
