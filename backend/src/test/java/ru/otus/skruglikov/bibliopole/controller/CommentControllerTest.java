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
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.repository.BookRepository;
import ru.otus.skruglikov.bibliopole.repository.CommentRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("класс CommentController должен")
@WebFluxTest
@ContextConfiguration(classes = {CommentController.class})
public class CommentControllerTest {

    @MockBean
    private CommentRepository commentRepository;
    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("Должен возвращать данные по комментарию")
    @Test
    void shouldGetComment() {
        webTestClient.get()
                .uri("/api/comment/"+ ObjectId.get())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @DisplayName("Должен добавлять комменатирй")
    @Test
    void shouldAddComment() {
        given(commentRepository.save(any()))
                .willReturn(Mono.just(new Comment()));
        webTestClient.post()
                .uri("/api/comment")
                .bodyValue(new Comment())
                .exchange()
                .expectStatus()
                .isOk();

    }

    @DisplayName("Должен обновлять данные по книге")
    @Test
    void shouldUpdateBook() {
        given(commentRepository.save(any()))
                .willReturn(Mono.just(new Comment()));
        webTestClient.put()
                .uri("/api/comment")
                .bodyValue(new Comment())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @DisplayName("Должен удалять комментарий по заданному id")
    @Test
    void shouldDeleteCommentById() {
        given(commentRepository.delete(any()))
                .willReturn(Mono.empty());
        webTestClient.delete()
                .uri("/api/comment/"+ObjectId.get())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @DisplayName("Должен возвращать для книги список комментариев")
    @Test
    void shouldGetCommentList() {
        webTestClient.get()
                .uri("/api/book/{bookId}/comment",ObjectId.get())
                .exchange()
                .expectStatus()
                .isOk();

    }


}
