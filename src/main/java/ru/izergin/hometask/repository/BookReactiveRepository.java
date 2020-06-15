package ru.izergin.hometask.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.izergin.hometask.domain.Book;

@Repository
public interface BookReactiveRepository extends ReactiveMongoRepository<Book, String> {
    Mono<Book> findByName(String name);
}
