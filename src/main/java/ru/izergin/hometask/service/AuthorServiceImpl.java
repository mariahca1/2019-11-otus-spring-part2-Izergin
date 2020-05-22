package ru.izergin.hometask.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.izergin.hometask.repository.AuthorReactiveRepository;
import ru.izergin.hometask.repository.AuthorRepository;
import ru.izergin.hometask.domain.Author;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorReactiveRepository authorReactiveRepository;

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> saveList(List<Author> authorList) {
        return authorList.stream().map(authorRepository::save).collect(Collectors.toList());
    }

    public void deleteAll() {
        authorRepository.deleteAll();
    }


    public Flux<Author> saveListReactive(List<Author> authorList) {
//        List<Author> res = new ArrayList<>();
//        if (authorList.size() != 0) {
//            for (Author author : authorList) {
//                res.add(authorRepository.save(author));
//            }
//        }
//        return Flux.generate(authorList.stream().map(authorRepository::save).collect(Collectors.toList()));
//        Consumer<Integer> printer = x-> System.out.printf("%d долларов \n", x);
//        return Flux.fromArray((Author[]) authorList.stream().map(authorReactiveRepository::save).toArray());
//        return Flux.fromArray((Author[])authorList.stream().map(authorRepository::save).collect(Collectors.toList()).toArray());
        return authorReactiveRepository.saveAll(authorList).delayElements(Duration.ofSeconds(1));

    }

}
