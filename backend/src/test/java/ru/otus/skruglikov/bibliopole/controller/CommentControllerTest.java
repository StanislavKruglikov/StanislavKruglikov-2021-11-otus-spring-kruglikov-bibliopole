package ru.otus.skruglikov.bibliopole.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.dto.CommentDTO;
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

    @DisplayName("Должен возвращать список комментариев")
    @Test
    void shouldGetCommentList() throws Exception {
        final Book book = new Book(1,null,null,null);
        final List<CommentDTO> expectedComments = List.of(
            CommentDTOAdapter.getDTO(new Comment(1,"text1",book)),
            CommentDTOAdapter.getDTO(new Comment(2,"text1",book))
            );
        when(commentService.readAllCommentsByBookId(book.getId()))
            .thenReturn(expectedComments);

        mockMvc.perform(get("/comment-list")
                .param("bookId",String.valueOf(book.getId())))
            .andExpect(status().isOk())
            .andExpect(view().name("lists/comment-list"))
            .andExpect(model().attribute("comments",hasSize(2)))
            .andExpect(model().attribute("comments", hasItems(expectedComments.get(0),expectedComments.get(1))))
            .andExpect(model().attribute("bookId",book.getId()));

    }

    @DisplayName("Должен сохранять данные по книге")
    @Test
    void shouldSaveComment() throws Exception {
        final CommentDTO expectedComment = CommentDTOAdapter.getDTO(
            new Comment(1,"text1",new Book(11,null,null,null))
        );
        doNothing()
            .when(commentService)
            .updateComment(expectedComment);

        mockMvc.perform(post("/comment-save")
                .param("action","save")
                .flashAttr("comment", expectedComment)
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("comment-list?bookId="+expectedComment.getBookId()));
        verify(commentService,times(1))
            .updateComment(expectedComment);
    }

    @DisplayName("Должен проверять данные формы до сохранения, при ошибках возвращать исходную форму")
    @Test
    void shouldValidateDataBeforeSaveComment() throws Exception {
        final CommentDTO wrongDataComment = new CommentDTO();
        doNothing()
            .when(commentService)
            .updateComment(wrongDataComment);

        mockMvc.perform(post("/comment-save")
                .param("action","save")
                .flashAttr("book", wrongDataComment)
            )
            .andExpect(status().isOk())
            .andExpect(view().name("forms/comment-edit"));
        verify(commentService,times(0))
            .updateComment(wrongDataComment);
    }

    @DisplayName("Должен удалять комментарий по заданному id")
    @Test
    void shouldDeleteCommentById() throws Exception {
        final long commentId = 11;
        doNothing()
            .when(commentService)
            .deleteComment(commentId);
        mockMvc.perform(delete("/comment")
                .param("id",String.valueOf(commentId)));
        verify(commentService,times(1))
            .deleteComment(commentId);
    }
}