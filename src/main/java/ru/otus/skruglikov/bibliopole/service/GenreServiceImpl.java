package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.skruglikov.bibliopole.dao.GenreDao;
import ru.otus.skruglikov.bibliopole.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public Genre readById(long genreId) {
        return genreDao.readById(genreId);
    }

    @Override
    public List<Genre> readAllGenres() {
        return genreDao.readAllGenres();
    }
}
