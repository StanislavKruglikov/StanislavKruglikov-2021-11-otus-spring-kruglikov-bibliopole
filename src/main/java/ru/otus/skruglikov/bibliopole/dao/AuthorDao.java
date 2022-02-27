package ru.otus.skruglikov.bibliopole.dao;

import ru.otus.skruglikov.bibliopole.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author readById(final long id);

    List<Author> readAllAuthors();
}
