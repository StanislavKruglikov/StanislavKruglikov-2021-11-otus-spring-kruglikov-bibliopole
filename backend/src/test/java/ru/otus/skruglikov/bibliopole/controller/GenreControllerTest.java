package ru.otus.skruglikov.bibliopole.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.service.GenreService;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("класс GenreController должен")
@WebMvcTest(GenreController.class)
public class GenreControllerTest {

    @MockBean
    private GenreService genreService;
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Должен возвращать список жанров")
    @Test
    void shouldGetGenreList() throws Exception {
        final List<Genre> expectedGenres = List.of(
            new Genre(1,"test11"),
            new Genre(2,"test21")
        );
        when(genreService.readAllGenres())
            .thenReturn(expectedGenres);

        mockMvc.perform(get("/genre"))
            .andExpect(status().isOk());
        verify(genreService,times(1))
            .readAllGenres();
    }

}
