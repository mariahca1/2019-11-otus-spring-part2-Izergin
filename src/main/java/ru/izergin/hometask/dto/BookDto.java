package ru.izergin.hometask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.izergin.hometask.domain.Author;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class BookDto {
    private String name;
    private String genreName;
    private Integer pageCount;
    private List<String> comments;
    private List<Author> authors;

    public BookDto(String name, String genreName, Integer pageCount) {
        this.name = name;
        this.genreName = genreName;
        this.pageCount = pageCount;
        this.comments = new ArrayList<>();
        this.authors = new ArrayList<>();
    }
    public BookDto addComment(String comment) {
        this.comments.add(comment);
        return this;
    }

    public BookDto addAuthor(Author author) {
        this.authors.add(author);
        return this;
    }
}
