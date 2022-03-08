package ru.otus.skruglikov.bibliopole.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "book")
public class Book {
    @Id
    private String id;
    private String title;
    private Genre genre;
    private Author author;
}

