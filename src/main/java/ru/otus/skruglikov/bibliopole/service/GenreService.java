package ru.otus.skruglikov.bibliopole.service;

import ru.otus.skruglikov.bibliopole.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre readById(long genreId);

    List<Genre> readAllGenres();

}
