package ru.izergin.hometask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain  = true)
public class Book {
    private Integer id;
    private String name;
    private Integer authorId;
    private Integer genreId;

    @Override
    public String toString() {
        return "Book name = \"" + this.name + "\", book author id = \"" + this.authorId + "\", book genre id = \"" + this.genreId + "\"";
    }
}