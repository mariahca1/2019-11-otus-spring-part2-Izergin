package ru.izergin.hometask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class BookWebDto {
    private String name;
    private String genreName;
    private Integer pageCount;
    private String authorFirstName;
    private String authorSecondName;
}
