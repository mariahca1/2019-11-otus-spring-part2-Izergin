package ru.izergin.hometask.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.izergin.hometask.domain.Author;

public interface AuthorDao extends MongoRepository<Author, Long> {

}
