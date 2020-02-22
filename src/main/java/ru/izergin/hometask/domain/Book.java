package ru.izergin.hometask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    @Field(name = "name")
    @Indexed(unique = true)
    private String name;

    @Field(name = "genre")
    private String genre;

    @Field(name = "page_count")
    private Integer pageCount;

    @Field(name = "comments")
    private List<String> comments;

    @DBRef
    private List<Author> authors;

    @Override
    public String toString() {
        return "Book id=" + id
                + ",name=\"" + this.name
                + "\",genre=\"" + this.genre
                + "\",pageCount=\"" + this.pageCount
                + "\",comments=" + Optional.ofNullable(this.comments.toString()).orElse("")
                + "\",authors=" + Optional.ofNullable(this.authors.toString()).orElse("")
                ;
    }

    public Book() {
        this.comments = new ArrayList<>();
        this.authors = new ArrayList<>();
    }

    public Book(String name, String genre, Integer pageCount) {
        this.name = name;
        this.genre = genre;
        this.pageCount = pageCount;
        this.comments = new ArrayList<>();
        this.authors = new ArrayList<>();
    }

    public Book addComment(String comment) {
        this.comments.add(comment);
        return this;
    }

    public Book addAuthor(Author author) {
        this.authors.add(author);
        return this;
    }
}