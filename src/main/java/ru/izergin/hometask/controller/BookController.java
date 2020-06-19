package ru.izergin.hometask.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.izergin.hometask.domain.Author;
import ru.izergin.hometask.domain.Book;
import ru.izergin.hometask.dto.BookDto;
import ru.izergin.hometask.service.BookServiceImpl;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {

    private BookServiceImpl bookService;

    @GetMapping(value = {"/main", "/"})
    public String mainPage(Model model) {
        List<Book> bookList = bookService.getAll();
        model.addAttribute("bookList", bookList);
        model.addAttribute("loggedUserName", getLoggedUserName());
        return "mainPage";
    }

    @GetMapping("/update")
    public String getUpdateBook(@RequestParam("id") String id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("updatedBook", book);
        model.addAttribute("loggedUserName", getLoggedUserName());
        return "editPage";
    }

    @PostMapping("/book/update")
    public String postUpdateBook(@ModelAttribute Book updatedBook, Model model) {
        bookService.update(updatedBook);
        model.addAttribute("updatedBook", updatedBook);
        model.addAttribute("loggedUserName", getLoggedUserName());
        return "editPage";
    }

    @GetMapping("/deleteComment")
    public String getDeleteComment(@RequestParam("bookId") String bookId, @RequestParam("commentNum") int commentNum, Model model) {
        Book book = bookService.deleteComment(bookId, commentNum);
        model.addAttribute("updatedBook", book);
        model.addAttribute("loggedUserName", getLoggedUserName());
        return "editPage";
    }

    @GetMapping("/addComment")
    public String getAddComment(@RequestParam("bookId") String bookId, @RequestParam("commentText") String commentText, Model model) {
        Book book = bookService.addComment(bookId, commentText);
        model.addAttribute("updatedBook", book);
        model.addAttribute("loggedUserName", getLoggedUserName());
        return "editPage";
    }

    @GetMapping("/deleteAuthor")
    public String getDeleteAuthor(@RequestParam("bookId") String bookId, @RequestParam("authorNum") int authorNum, Model model) {
        Book book = bookService.deleteAuthor(bookId, authorNum);
        model.addAttribute("updatedBook", book);
        model.addAttribute("loggedUserName", getLoggedUserName());
        return "editPage";
    }

    @GetMapping("/addAuthor")
    public String getAddAuthor(@RequestParam("bookId") String bookId, Model model) {
        Book book = bookService.addAuthor(bookId, new Author("", ""));
        model.addAttribute("updatedBook", book);
        model.addAttribute("loggedUserName", getLoggedUserName());
        return "editPage";
    }

    @GetMapping("/book/delete")
    public String getDeleteBook(@RequestParam("id") String id, Model model) {
        bookService.deleteById(id);
        List<Book> bookList = bookService.getAll();
        model.addAttribute("bookList", bookList);
        model.addAttribute("loggedUserName", getLoggedUserName());
        return "mainPage";
    }

    @GetMapping("/create")
    public String getCreateBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("loggedUserName", getLoggedUserName());
        return "createPage";
    }

    @PostMapping("/book/create")
    public String postCreateBook(@ModelAttribute Book book, Model model) {
        bookService.save(new BookDto(book.getName(), book.getGenre(), book.getPageCount())
                .addAuthor(new Author(book.getAuthors().get(0).getFirstName(), book.getAuthors().get(0).getSecondName()))
        );
        List<Book> bookList = bookService.getAll();
        model.addAttribute("bookList", bookList);
        model.addAttribute("loggedUserName", getLoggedUserName());
        return "mainPage";
    }

    public String getLoggedUserName() {
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        } catch (Exception e) {
            return "Err: user not detected";
        }
    }
}

