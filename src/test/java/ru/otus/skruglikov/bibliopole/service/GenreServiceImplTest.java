package ru.otus.skruglikov.bibliopole.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.repository.GenreRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("класс GenreServiceImpl должен")
@SpringBootTest(classes = GenreServiceImpl.class)
public class GenreServiceImplTest {

    @Autowired
    private GenreServiceImpl genreService;
    @MockBean
    private GenreRepository genreRepository;

    @DisplayName("возвращать жанр по указанному id")
    @Test
    void shouldReadAuthorById() {
        final Genre expectedGenre = new Genre(1,"тест жанр");
        when(genreRepository.findById(1L))
                .thenReturn(Optional.of(expectedGenre));
        assertEquals(expectedGenre,genreService.readById(1));
    }

    @DisplayName("возвращать все жанры")
    @Test
    void shouldReadAllAuthors() {
        List<String> g = Arrays.asList("тест жанр");
        final List<Genre> expectedGenreList = Arrays.asList(new Genre(1,"тест жанр"),
                new Genre(2,"тест жанр2"));
        when(genreRepository.findAll())
                .thenReturn(expectedGenreList);
        assertThat(genreService.readAllGenres())
                .containsExactlyInAnyOrderElementsOf(expectedGenreList);
    }
}
