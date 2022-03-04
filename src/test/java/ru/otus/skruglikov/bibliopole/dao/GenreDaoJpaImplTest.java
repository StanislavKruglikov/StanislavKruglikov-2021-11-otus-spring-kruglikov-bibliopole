package ru.otus.skruglikov.bibliopole.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.exception.GenreNotFoundDaoException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("класс GenreDaoJpaImpl должен")
@DataJpaTest
@Import(GenreDaoJpaImpl.class)
class GenreDaoJpaImplTest {

    @Autowired
    private GenreDaoJpaImpl genreDao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать жанр по указанному id")
    @Test
    void shouldReadGenreById() {
        final Genre expectedGenre = new Genre(1, "тест жанр");
        assertEquals(expectedGenre,genreDao.findById(1));
    }

    @DisplayName("возвращать список всех жанров")
    @Test
    void shouldReadAllGenres() {
        final List<Genre> expectedGenreList = List.of(new Genre(1, "тест жанр"),
                new Genre(2, "тест жанр2"));
        assertThat(genreDao.readAllGenres())
                .containsExactlyInAnyOrderElementsOf(expectedGenreList);
    }

    @DisplayName("возвращать ошибку если жанр не найден")
    @Test
    void shouldThrowGenreNotFoundException() {
        assertThrowsExactly(GenreNotFoundDaoException.class,() -> genreDao.findById(99));
    }
}