package ru.otus.skruglikov.bibliopole.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.dto.BookDTO;
import ru.otus.skruglikov.bibliopole.dto.CommentDTO;
import ru.otus.skruglikov.bibliopole.dto.adapter.BookDTOAdapter;
import ru.otus.skruglikov.bibliopole.dto.adapter.CommentDTOAdapter;
import ru.otus.skruglikov.bibliopole.service.AuthorService;
import ru.otus.skruglikov.bibliopole.service.CommentService;
import ru.otus.skruglikov.bibliopole.service.GenreService;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("класс CommentController должен")
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Должен возвращать данные по комментарию")
    @Test
    void shouldGetComment() throws Exception {
        final CommentDTO expectedComment = CommentDTOAdapter.getDTO(
            new Comment(1,"text1",new Book(11,null,null,null))
        );
        when(commentService.readCommentById(expectedComment.getId()))
            .thenReturn(expectedComment);

        mockMvc.perform(get("/comment/{id}",expectedComment.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(expectedComment.getId()))
            .andExpect(jsonPath("$.text").value(expectedComment.getText()));
    }

    @DisplayName("Должен добавлять комменатирй")
    @Test
    void shouldAddComment() throws Exception {
        final Book book = new Book(1,"text1",new Genre(1,""),
            new Author(1,"","",""));
        final CommentDTO commentDTO = CommentDTOAdapter.getDTO(new Comment(0,"comment1",book));
        final String commentDTOAsString = "{\"id\": 0, \"text\": \"comment1\", \"bookId\": 1}";
        when(commentService.createComment(commentDTO))
            .thenReturn(commentDTO);
        mockMvc.perform(post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(commentDTOAsString))
            .andExpect(status().isOk());
        verify(commentService,times(1))
            .createComment(commentDTO);
    }

    @DisplayName("Должен обновлять данные по книге")
    @Test
    void shouldUpdateBook() throws Exception {
        final Book book = new Book(1,"text1",new Genre(1,""),
            new Author(1,"","",""));
        final CommentDTO commentDTO = CommentDTOAdapter.getDTO(new Comment(1,"comment1",book));
        final String commentDTOAsString = "{\"id\": 1, \"text\": \"comment1\", \"bookId\": 1}";
        when(commentService.updateComment(commentDTO))
            .thenReturn(commentDTO);
        mockMvc.perform(put("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(commentDTOAsString))
            .andExpect(status().isOk());
        verify(commentService,times(1))
            .updateComment(commentDTO);
    }

    @DisplayName("Должен удалять комментарий по заданному id")
    @Test
    void shouldDeleteCommentById() throws Exception {
        final long commentId = 11;
        doNothing()
            .when(commentService)
            .deleteComment(commentId);
        mockMvc.perform(delete("/comment/{id}",commentId))
            .andExpect(status().isOk());
        verify(commentService,times(1))
            .deleteComment(commentId);
    }
}
