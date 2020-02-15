package ru.izergin.hometask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.izergin.hometask.domain.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {

}
