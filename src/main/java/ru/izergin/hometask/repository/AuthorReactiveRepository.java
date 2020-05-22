package ru.izergin.hometask.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.izergin.hometask.domain.Author;

public interface AuthorReactiveRepository extends ReactiveMongoRepository<Author, Long> {

}
