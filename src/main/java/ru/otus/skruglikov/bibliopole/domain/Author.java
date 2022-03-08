package ru.otus.skruglikov.bibliopole.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "author")
public class Author {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String patronymicName;
}
