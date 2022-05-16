package ru.otus.skruglikov.bibliopole.controller;

import org.assertj.core.api.Assertions;
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
import ru.otus.skruglikov.bibliopole.service.BookService;
import ru.otus.skruglikov.bibliopole.service.CommentService;
import ru.otus.skruglikov.bibliopole.service.GenreService;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("класс BookController должен")
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Должен возвращать список книг")
    @Test
    void shouldGetBookList() throws Exception {
        final List<BookDTO> expectedBooks = List.of(
            BookDTOAdapter.getDTO(new Book(1,"title1",new Genre(1,"genre1"),
                new Author(1,"test11","test12","test13"))),
            BookDTOAdapter.getDTO(new Book(2,"test2",new Genre(1,"genre1"),
                new Author(2,"test21","test22","test23")))
        );
        when(bookService.readAllBooks())
            .thenReturn(expectedBooks);

        mockMvc.perform(get("/book"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.*",hasSize(2)))
            .andExpect(jsonPath("$[0].id").value("1"))
            .andExpect(jsonPath("$[1].id").value("2"));
    }

    @DisplayName("Должен возвращать книгу")
    @Test
    void shouldGetBook() throws Exception {
        final BookDTO expectedBook = BookDTOAdapter.getDTO(new Book(1,"title1",new Genre(1,"genre1"),
                new Author(1,"test11","test12","test13")));
        when(bookService.readBookById(expectedBook.getId()))
            .thenReturn(expectedBook);

        mockMvc.perform(get("/book/{id}",expectedBook.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(expectedBook.getId()))
            .andExpect(jsonPath("$.title").value(expectedBook.getTitle()));
    }

    @DisplayName("Должен удалять книгу по заданному id")
    @Test
    void shouldDeleteBookById() throws Exception {
        final long bookId = 11;
        doNothing()
            .when(bookService)
            .deleteBook(bookId);
        mockMvc.perform(delete("/book/"+bookId))
            .andExpect(status().isOk());
        verify(bookService,times(1))
            .deleteBook(bookId);
    }

    @DisplayName("Должен добавлять книгу")
    @Test
    void shouldAddBook() throws Exception {
        final BookDTO bookDTO = BookDTOAdapter.getDTO(new Book(0,"test1",new Genre(1,""),
            new Author(1,"","","")));
        final String bookDTOAsString = "{\"id\": 0, \"title\": \"test1\", \"genreId\": 1, \"genreName\": \"\", \"authorId\": 1, \"authorName\": \"\"}";
            when(bookService.createBook(bookDTO))
                .thenReturn(bookDTO);
        mockMvc.perform(post("/book")
            .contentType(MediaType.APPLICATION_JSON)
            .content(bookDTOAsString))
            .andExpect(status().isOk());
        verify(bookService,times(1))
            .createBook(bookDTO);
    }

    @DisplayName("Должен обновлять данные по книге")
    @Test
    void shouldUpdateBook() throws Exception {
        final BookDTO bookDTO = BookDTOAdapter.getDTO(new Book(1,"test1",new Genre(1,""),
            new Author(1,"","","")));
        final String bookDTOAsString = "{\"id\": 1, \"title\": \"test1\", \"genreId\": 1, \"genreName\": \"\", \"authorId\": 1, \"authorName\": \"\"}";
        when(bookService.updateBook(bookDTO))
            .thenReturn(bookDTO);
        mockMvc.perform(put("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookDTOAsString))
            .andExpect(status().isOk());
        verify(bookService,times(1))
            .updateBook(bookDTO);
    }

    @DisplayName("Должен возвращать для книги список комментариев")
    @Test
    void shouldGetCommentList() throws Exception {
        final Book book = new Book(1,null,null,null);
        final List<CommentDTO> expectedComments = List.of(
            CommentDTOAdapter.getDTO(new Comment(1,"text1",book)),
            CommentDTOAdapter.getDTO(new Comment(2,"text1",book))
        );
        when(commentService.readAllCommentsByBookId(book.getId()))
            .thenReturn(expectedComments);

        mockMvc.perform(get("/book/{bookId}/comment",book.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.*",hasSize(2)))
            .andExpect(jsonPath("$[0].id").value("1"))
            .andExpect(jsonPath("$[1].id").value("2"));

    }
}
