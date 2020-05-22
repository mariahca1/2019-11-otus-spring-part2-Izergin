package ru.izergin.hometask.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.izergin.hometask.repository.BookReactiveRepository;
import ru.izergin.hometask.repository.BookRepository;
import ru.izergin.hometask.domain.Author;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.dto.BookDto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorServiceImpl authorService;
    private final BookReactiveRepository bookReactiveRepository;


    public void test() {
        System.out.println("start");
//        Mono<Book> bookMono = bookReactiveRepository.save(new Book("n1", "g", 123));
//        bookMono.subscribe(); //не блокирует
//        bookMono.subscribe(System.out::println);

        Mono<String> data = Mono.just("foo");
        Disposable subscribe = data.subscribe(System.out::println);
//        System.out.println(data);

        Flux<Integer> ints = Flux.range(1, 4).delayElements(Duration.ofSeconds(1)).delaySubscription(Duration.ofSeconds(1));
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));


        Flux<Book> bookFlux = bookReactiveRepository.findAll();
        bookFlux.subscribe(book -> System.out.println(book));


        System.out.println("finish");
    }

    public void test1() {
        final long timeStarted = System.currentTimeMillis();
        authorService.deleteAll();
        deleteAll();
        //попытка создать транзакцию из двух запросов, сохранить книгу с авторами. ПО окончании обработки - вернуть ок
        //zip
        BookDto bookDto = new BookDto("BOOK_NAME_1", "GENRE_CLASSIC", 5)
                .addAuthor(new Author("Иван", "Иванов"))
//                .addAuthor(new Author("Петр", "Петров"))
                .addComment("Комментарий 1")
                .addComment("Комментарий 2");

//        authorService.saveList(bookDto.getAuthors());

//        Book book1 = save(new BookDto("BOOK_NAME_1", "GENRE_CLASSIC", 5)
//                .addAuthor(new Author("Иван", "Иванов"))
//                .addAuthor(new Author("Петр", "Петров"))
//                .addComment("Комментарий 1")
//                .addComment("Комментарий 2"));
//        List<Author> authorList = new ArrayList<>();
//        authorList.add(new Author("Иван1", "Иванов"));
//        authorList.add(new Author("Иван2", "Иванов"));
        Flux<Author> authorFlux = authorService.saveListReactive(bookDto.getAuthors());
//        authorFlux.subscribe(x -> {
//            System.out.println("Wait time authors " + (System.currentTimeMillis() - timeStarted));
////            System.out.println(x);
//        });

        Book book = new Book(bookDto.getName(), bookDto.getGenreName(), bookDto.getPageCount())
                .setAuthors(bookDto.getAuthors())
                .setComments(bookDto.getComments());
        Mono<Book> bookMono = bookReactiveRepository.save(book).delaySubscription(Duration.ofSeconds(2));
//        Mono<Book> bookMono = save(book).delaySubscription(Duration.ofSeconds(2));
//        bookMono.subscribe(x -> {
//            System.out.println("Wait time book " + (System.currentTimeMillis() - timeStarted));
////            System.out.println(x);
//        });

        authorFlux.zipWith(bookMono).subscribe(x -> {
            System.out.println("Wait time authors + book " + (System.currentTimeMillis() - timeStarted));
//            System.out.println(x);
        });

        System.out.println("ok");

        System.out.println("Wait time " + (System.currentTimeMillis() - timeStarted));
    }


    //на вторник - дописать тесты для MVC

//    public Mono<Book> save(Book book) {
//        bookReactiveRepository.save(book).delaySubscription(Duration.ofSeconds(2));
//        throw new NullPointerException("q");
//    }

    @Transactional(readOnly = true)
    public Long getCount() {
        return bookRepository.count();
    }

    public Book findById(String id) {
        return bookRepository.findById(id);
    }

    @SneakyThrows
    @Transactional
    //сохранение книги
    public Book save(BookDto bookDto) {
        List<Author> authorList = authorService.saveList(bookDto.getAuthors());
        Book book = new Book(bookDto.getName(), bookDto.getGenreName(), bookDto.getPageCount())
                .setAuthors(authorList)
                .setComments(bookDto.getComments());
        return bookRepository.save(book);
    }

    @SneakyThrows
    @Transactional
    //обновление книги
    public Book update(Book book) {
        List<Author> authorList = authorService.saveList(book.getAuthors());
        return bookRepository.save(book).setAuthors(authorList);
    }

    @Transactional
    public Book deleteComment(String bookId, int commentNum) {
        Book book = bookRepository.findById(bookId);
        book.getComments().remove(commentNum);
        return bookRepository.save(book);
    }

    @Transactional
    public Book addComment(String bookId, String commentText) {
        Book book = bookRepository.findById(bookId);
        book.addComment(commentText);
        return update(book);
    }

    @Transactional
    public Book deleteAuthor(String bookId, int authorNum) {
        Book book = bookRepository.findById(bookId);
        book.getAuthors().remove(authorNum);
        return update(book);
    }

    @Transactional
    public Book addAuthor(String bookId, Author author) {
        Book book = bookRepository.findById(bookId).addAuthor(author);
        return update(book);
    }

    @Transactional
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Transactional
    public void deleteByName(String name) {
        bookRepository.deleteByName(name);
    }

    @Transactional
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<Book> findByGenreFirstLetter(String letter) {
        return bookRepository.findByGenreFirstLetter(letter);
    }
}
