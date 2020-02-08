package ru.izergin.hometask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @OneToMany(targetEntity = BookComment.class, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "book_id", nullable = false)
    private List<BookComment> comments;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinTable(name = "book_author_links", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @Override
    public String toString() {
        return "Book id=" + id
                + ",name=\"" + this.name
                + "\",genre=\"" + this.genre.toString()
                + "\",comments=" + Optional.ofNullable(this.comments.toString()).orElse("");
    }

    public Book(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
        this.comments = new ArrayList<>();
    }

    public Book(String name, Genre genre, List<BookComment> comments) {
        this.name = name;
        this.genre = genre;
        this.comments = comments;
    }

    public void addComment(String comment)
    {
        this.comments.add(new BookComment(comment));
    }
}