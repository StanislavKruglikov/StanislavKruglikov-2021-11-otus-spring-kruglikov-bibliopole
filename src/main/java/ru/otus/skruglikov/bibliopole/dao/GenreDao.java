package ru.otus.skruglikov.bibliopole.dao;

import ru.otus.skruglikov.bibliopole.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre readByCode(final String code);

    List<Genre> readAllGenres();
}
