package ru.otus.skruglikov.bibliopole.service;

import ru.otus.skruglikov.bibliopole.domain.Author;

import java.util.List;

public interface AuthorService {

    Author readById(long authorId);

    List<Author> readAllAuthors();

}
