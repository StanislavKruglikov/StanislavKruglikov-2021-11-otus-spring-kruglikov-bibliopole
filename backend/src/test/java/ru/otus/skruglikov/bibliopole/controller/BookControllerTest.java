package ru.otus.skruglikov.bibliopole.controller;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.repository.BookRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static reactor.core.publisher.Mono.when;

@DisplayName("класс BookController должен")
@WebFluxTest
@ContextConfiguration(classes = {BookController.class})
public class BookControllerTest {

    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("Должен возвращать список книг")
    @Test
    void shouldGetBookList() {
        webTestClient.get()
                .uri("/api/book")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @DisplayName("Должен возвращать книгу")
    @Test
    void shouldGetBook() {
        webTestClient.get()
                .uri("/api/book/"+ObjectId.get())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @DisplayName("Должен удалять книгу по заданному id")
    @Test
    void shouldDeleteBookById() {
        given(bookRepository.deleteById(anyString()))
                .willReturn(Mono.empty());
        webTestClient.delete()
                .uri("/api/book/"+ObjectId.get())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @DisplayName("Должен добавлять книгу")
    @Test
    void shouldAddBook() {
        given(bookRepository.save(any()))
                .willReturn(Mono.just(new Book()));
        webTestClient.post()
                .uri("/api/book")
                .bodyValue(new Book())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @DisplayName("Должен обновлять данные по книге")
    @Test
    void shouldUpdateBook() throws Exception {
        given(bookRepository.save(any()))
                .willReturn(Mono.just(new Book()));
        webTestClient.put()
                .uri("/api/book")
                .bodyValue(new Book())
                .exchange()
                .expectStatus()
                .isOk();
    }
}
