package ru.otus.skruglikov.bibliopole.domain;

import lombok.Data;

@Data
public class Book {
    private final Long id;
    private final String title;
    private final Genre genre;
    private final Author author;
}
