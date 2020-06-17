package ru.izergin.hometask.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.izergin.hometask.repository.AuthorReactiveRepository;
import ru.izergin.hometask.repository.BookReactiveRepository;
import ru.izergin.hometask.domain.Author;
import ru.izergin.hometask.domain.Book;

@Service
@AllArgsConstructor
public class BookServiceImpl //данный класс оставил только для инициализации - создания книги + авторов
{

    private final BookReactiveRepository bookReactiveRepository;
    private final AuthorReactiveRepository authorReactiveRepository;

    public void init() {
        authorReactiveRepository.deleteAll().subscribe();
        bookReactiveRepository.deleteAll().subscribe();

        Book book = new Book("BOOK_NAME_1", "GENRE_CLASSIC", 5)
                .addAuthor(new Author("Иван", "Иванов"))
                .addAuthor(new Author("Иван2", "Иванов2"))
                .addAuthor(new Author("Петр", "Петров"))
                .addComment("Комментарий 1")
                .addComment("Комментарий 2");

        bookReactiveRepository.findByName(book.getName())
                .map((x) -> ResponseEntity.status(HttpStatus.CONFLICT.value()).body(book)) //если книга найдена - ошибка
                .switchIfEmpty(authorReactiveRepository.saveAll(book.getAuthors())
                        .last() //только последнего автора. Для трансформации Flux->Mono
                        .flatMap((q) -> bookReactiveRepository.save(book).map(b -> ResponseEntity.ok(b)))).subscribe();
    }
}
