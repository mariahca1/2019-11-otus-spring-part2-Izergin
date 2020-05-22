package ru.izergin.hometask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static java.util.Objects.isNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
//@CompoundIndex(def = "{'firstName':1, 'secondName':1}", name = "compound_index")
public class Author {
    @Id
    private String id;

    @Field(name = "first_name")
    private String firstName;

    @Field(name = "second_name")
    private String secondName;

    @Override
    public String toString() {
        return "[id=" + (isNull(id) ? 0 : id) + ",first_name=" + firstName + ",second_name=" + secondName + "]";
    }

    public Author(String firstName, String secondName){
        this.firstName = firstName;
        this.secondName = secondName;
    }
}