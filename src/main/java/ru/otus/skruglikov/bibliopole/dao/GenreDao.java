package ru.otus.skruglikov.bibliopole.dao;

import ru.otus.skruglikov.bibliopole.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre readById(final long id);

    List<Genre> readAllGenres();
}
