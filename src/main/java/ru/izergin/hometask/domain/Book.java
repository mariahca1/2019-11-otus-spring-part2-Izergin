package ru.izergin.hometask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain  = true)
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}


//    @OneToMany(targetEntity = BookComment.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "book_id", nullable = false)
//    private List<BookComment> bookCommentList;
//
//    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY)
//    @JoinTable(name = "book_author_links",joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
//    private List<Author> authorList;

//    public void addAuthor(Author author) {
//        this.authorList.add(author);
//    }

//    @Override
//    public String toString() {
//        return "Book name = \"" + this.name + "\", book author id = \"" + this.authorList + "\", book genre id = \"" + this.genre + "\"";
//    }

//    public Book(String name, Genre genre){
//        this.id = 0;
//        this.name = name;
//        this.genre = genre;
//    }