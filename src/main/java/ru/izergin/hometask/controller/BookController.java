package ru.izergin.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.izergin.hometask.domain.Author;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.dto.BookDto;
import ru.izergin.hometask.dto.BookWebDto;
import ru.izergin.hometask.service.BookServiceImpl;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/main")
    public String mainPage(Model model) {
        List<Book> bookList = bookService.getAll();
        model.addAttribute("bookList", bookList);
        return "mainPage";
    }

    @GetMapping("/update")
    public String getUpdateBook(@RequestParam("id") String id, Model model) {
        Book book = bookService.findById(id);
        List<String> comments = book.getComments(); //тем самым фетчим и комментарии
        model.addAttribute("updatedBook", book);
        return "editPage";
    }

    @PostMapping("/update")
    public String postUpdateBook(@ModelAttribute Book updatedBook, Model model) {
        bookService.update(updatedBook);
        model.addAttribute("updatedBook", updatedBook);
        return "editPage";
    }

    @GetMapping("/deleteComment")
    public String getDeleteComment(@RequestParam("bookId") String bookId, @RequestParam("commentNum") int commentNum, Model model) {
        Book book = bookService.deleteComment(bookId, commentNum);
        model.addAttribute("updatedBook", book);
        return "editPage";
    }

    @GetMapping("/addComment")
    public String getAddComment(@RequestParam("bookId") String bookId, @RequestParam("commentText") String commentText, Model model) {
        Book book = bookService.addComment(bookId, commentText);
        model.addAttribute("updatedBook", book);
        return "editPage";
    }

    @GetMapping("/deleteAuthor")
    public String getDeleteAuthor(@RequestParam("bookId") String bookId, @RequestParam("authorNum") int authorNum, Model model) {
        System.out.println(bookId);
        System.out.println(authorNum);
        Book book = bookService.deleteAuthor(bookId, authorNum);
        model.addAttribute("updatedBook", book);
        return "editPage";
    }

    @GetMapping("/addAuthor")
    public String getAddAuthor(@RequestParam("bookId") String bookId, Model model) {
        Book book = bookService.addAuthor(bookId, new Author("", ""));
        model.addAttribute("updatedBook", book);
        return "editPage";
    }

    @GetMapping("/delete")
    public String getDeleteBook(@RequestParam("id") String id, Model model) {
        bookService.deleteById(id);
        List<Book> bookList = bookService.getAll();
        model.addAttribute("bookList", bookList);
        return "mainPage";
    }

    @GetMapping("/create")
    public String getCreateBook(Model model) {
        model.addAttribute(new BookWebDto());
        return "createPage";
    }

    @PostMapping("/create")
    public String postCreateBook(@ModelAttribute BookWebDto bookWebDto, Model model) {
        System.out.println(bookWebDto);
        bookService.save(new BookDto(bookWebDto.getName(), bookWebDto.getGenreName(), bookWebDto.getPageCount())
                .addAuthor(new Author(bookWebDto.getAuthorFirstName(), bookWebDto.getAuthorSecondName()))
        );
        List<Book> bookList = bookService.getAll();
        model.addAttribute("bookList", bookList);
        return "mainPage";
    }
}

