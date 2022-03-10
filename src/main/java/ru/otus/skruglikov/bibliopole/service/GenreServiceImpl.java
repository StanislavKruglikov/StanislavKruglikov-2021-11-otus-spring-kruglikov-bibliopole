package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.exception.GenreNotFoundDaoException;
import ru.otus.skruglikov.bibliopole.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Genre readById(final String genreId) {
        return genreRepository
                .findById(genreId)
                .orElseThrow(()-> new GenreNotFoundDaoException("указан не корректный код жанра - " + genreId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> readAllGenres() {
        return genreRepository.findAll();
    }
}
