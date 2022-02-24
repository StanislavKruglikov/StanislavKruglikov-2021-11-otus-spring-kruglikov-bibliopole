package ru.otus.skruglikov.bibliopole.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.skruglikov.bibliopole.dao.GenreDao;
import ru.otus.skruglikov.bibliopole.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("класс GenreServiceImpl должен")
@SpringBootTest(classes = GenreServiceImpl.class)
public class GenreServiceImplTest {

    @Autowired
    private GenreServiceImpl genreService;
    @MockBean
    private GenreDao genreDao;

    @DisplayName("возвращать жанр по указанному id")
    @Test
    void shouldReadAuthorById() {
        final Genre expectedGenre = new Genre("test_code","тест жанр");
        when(genreDao.readByCode("test_code"))
                .thenReturn(expectedGenre);
        assertEquals(expectedGenre,genreService.readByCode("test_code"));
    }

    @DisplayName("возвращать все жанры")
    @Test
    void shouldReadAllAuthors() {
        final List<Genre> expectedGenreList = List.of(new Genre("test_code","тест жанр"),
                new Genre("test_code2","тест жанр2"));
        when(genreDao.readAllGenres())
                .thenReturn(expectedGenreList);
        assertThat(genreService.readAllGenres())
                .containsExactlyInAnyOrderElementsOf(expectedGenreList);
    }
}
