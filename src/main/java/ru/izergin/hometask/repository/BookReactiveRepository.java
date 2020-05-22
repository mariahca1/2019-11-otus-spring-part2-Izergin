package ru.izergin.hometask.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.izergin.hometask.domain.Book;

@Repository
public interface BookReactiveRepository extends ReactiveMongoRepository<Book, Integer> {
}
