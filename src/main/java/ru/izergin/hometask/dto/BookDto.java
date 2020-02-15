package ru.izergin.hometask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class BookDto {
    private String name;
    private String genreName;
    private List<String> comments;

    public BookDto(String name, String genreName) {
        this.name = name;
        this.genreName = genreName;
    }
}
