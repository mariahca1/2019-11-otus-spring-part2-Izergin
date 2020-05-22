package ru.izergin.hometask.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.izergin.hometask.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, Long> {

}
