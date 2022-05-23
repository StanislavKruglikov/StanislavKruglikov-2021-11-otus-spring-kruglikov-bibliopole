package ru.otus.skruglikov.bibliopole.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.otus.skruglikov.bibliopole.repository.GenreRepository;

@DisplayName("класс GenreController должен")
@WebFluxTest
@ContextConfiguration(classes = {GenreController.class})
public class GenreControllerTest {

    @MockBean
    private GenreRepository authorRepository;
    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("Должен возвращать список жанров")
    @Test
    void shouldGetGenreList() throws Exception {
        webTestClient
                .get()
                .uri("/api/genre")
                .exchange()
                .expectStatus()
                .isOk();
    }

}
