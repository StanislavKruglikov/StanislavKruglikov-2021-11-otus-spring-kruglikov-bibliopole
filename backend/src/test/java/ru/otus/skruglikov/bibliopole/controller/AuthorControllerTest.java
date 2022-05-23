package ru.otus.skruglikov.bibliopole.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.otus.skruglikov.bibliopole.repository.AuthorRepository;

@DisplayName("класс AuthorController должен")
@WebFluxTest
@ContextConfiguration(classes = {AuthorController.class})
public class AuthorControllerTest {

    @MockBean
    private AuthorRepository authorRepository;
    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("Должен возвращать список авторов")
    @Test
    void shouldGetAuthorList() throws Exception {
        webTestClient
                .get()
                .uri("/api/author")
                .exchange()
                .expectStatus()
                .isOk();
    }

}
