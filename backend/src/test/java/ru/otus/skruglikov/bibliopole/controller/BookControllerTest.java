package ru.otus.skruglikov.bibliopole.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.dto.BookDTO;
import ru.otus.skruglikov.bibliopole.dto.adapter.BookDTOAdapter;
import ru.otus.skruglikov.bibliopole.service.AuthorService;
import ru.otus.skruglikov.bibliopole.service.BookService;
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
            .andExpect(status().isOk());
    }

    @DisplayName("Должен удалять книгу по заданному id")
    @Test
    void shouldDeleteBookById() throws Exception {
        final long bookId = 11;
        doNothing()
            .when(bookService)
            .deleteBook(bookId);
        mockMvc.perform(delete("/book")
                .param("id",String.valueOf(bookId))
            ).andExpect(status().isOk());
        verify(bookService,times(1))
            .deleteBook(bookId);
    }
}
