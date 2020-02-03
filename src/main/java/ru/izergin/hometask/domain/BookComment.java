package ru.izergin.hometask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

import static java.util.Objects.isNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain  = true)
@Entity
@Table(name = "book_comments")
public class BookComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Override
    public String toString() {
        return "[id=" + (isNull(id) ? 0 : id) + ",comment=" + comment + "]";
    }

    public BookComment(String comment) {
        this.comment = comment;
    }
}