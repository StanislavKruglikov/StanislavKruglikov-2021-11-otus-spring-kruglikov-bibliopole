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
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("класс BookController должен")
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;

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

        mockMvc.perform(get("/book-list"))
            .andExpect(status().isOk())
            .andExpect(view().name("lists/book-list"))
            .andExpect(model().attribute("books",hasSize(2)))
            .andExpect(model().attribute("books", hasItems(expectedBooks.get(0),expectedBooks.get(1))));

    }

    @DisplayName("Должен возвращать страницу для редактирования книги")
    @Test
    void shouldReturnPageForEditBook() throws Exception {
        final BookDTO expectedBook = BookDTOAdapter.getDTO(
            new Book(1,"title1",new Genre(1,"genre1"),
            new Author(1,"test11","test12","test13"))
        );
        final List<Author> expectedAuthors = List.of(
            new Author(1,"test11","test12","test13"),
            new Author(2,"test21","test22","test23")
        );
        final List<Genre> expectedGenres = List.of(
            new Genre(1,"test11"),
            new Genre(2,"test21")
        );
        when(bookService.readBookById(1))
            .thenReturn(expectedBook);
        when(authorService.readAllAuthors())
            .thenReturn(expectedAuthors);
        when(genreService.readAllGenres())
            .thenReturn(expectedGenres);

        mockMvc.perform(get("/book-edit")
                .queryParam("bookId",String.valueOf(expectedBook.getId()))
            ).andExpect(status().isOk())
            .andExpect(view().name("forms/book-edit"))
            .andExpect(model().attribute("book",expectedBook))
            .andExpect(model().attribute("authors",hasSize(2)))
            .andExpect(model().attribute("authors",hasItems(expectedAuthors.get(0),expectedAuthors.get(1))))
            .andExpect(model().attribute("genres",hasSize(2)))
            .andExpect(model().attribute("genres",hasItems(expectedGenres.get(0),expectedGenres.get(1))));
    }

    @DisplayName("Должен возвращать страницу для создания книги")
    @Test
    void shouldReturnPageForAddBook() throws Exception {
        final BookDTO expectedBook = new BookDTO();
        final List<Author> expectedAuthors = List.of(
            new Author(1,"test11","test12","test13"),
            new Author(2,"test21","test22","test23")
        );
        final List<Genre> expectedGenres = List.of(
            new Genre(1,"test11"),
            new Genre(2,"test21")
        );
        when(authorService.readAllAuthors())
            .thenReturn(expectedAuthors);
        when(genreService.readAllGenres())
            .thenReturn(expectedGenres);

        mockMvc.perform(get("/book-edit")
                .queryParam("bookId","0")
            ).andExpect(status().isOk())
            .andExpect(view().name("forms/book-edit"))
            .andExpect(model().attribute("book",expectedBook))
            .andExpect(model().attribute("authors",hasSize(2)))
            .andExpect(model().attribute("authors",hasItems(expectedAuthors.get(0),expectedAuthors.get(1))))
            .andExpect(model().attribute("genres",hasSize(2)))
            .andExpect(model().attribute("genres",hasItems(expectedGenres.get(0),expectedGenres.get(1))));
    }

    @DisplayName("Должен сохранять данные по книге")
    @Test
    void shouldSaveBook() throws Exception {
        final BookDTO expectedBook = BookDTOAdapter.getDTO(
            new Book(1,"title1",new Genre(1,"genre1"),
                new Author(1,"test11","test12","test13"))
        );
        final List<Author> expectedAuthors = List.of(
            new Author(1,"test11","test12","test13"),
            new Author(2,"test21","test22","test23")
        );
        final List<Genre> expectedGenres = List.of(
            new Genre(1,"test11"),
            new Genre(2,"test21")
        );
        doNothing()
            .when(bookService)
            .updateBook(expectedBook);
        when(authorService.readAllAuthors())
            .thenReturn(expectedAuthors);
        when(genreService.readAllGenres())
            .thenReturn(expectedGenres);

        mockMvc.perform(post("/book-save")
                .param("action","save")
                .flashAttr("book", expectedBook)
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("book-list"));
        verify(bookService,times(1))
            .updateBook(expectedBook);
    }

    @DisplayName("Должен проверять данные формы до сохранения, при ошибках возвращать исходную форму")
    @Test
    void shouldValidateDataBeforeSaveBook() throws Exception {
        final BookDTO wrongDataBook = new BookDTO();
        final List<Author> expectedAuthors = List.of(
            new Author(1,"test11","test12","test13"),
            new Author(2,"test21","test22","test23")
        );
        final List<Genre> expectedGenres = List.of(
            new Genre(1,"test11"),
            new Genre(2,"test21")
        );
        doNothing()
            .when(bookService)
            .updateBook(wrongDataBook);
        when(authorService.readAllAuthors())
            .thenReturn(expectedAuthors);
        when(genreService.readAllGenres())
            .thenReturn(expectedGenres);

        mockMvc.perform(post("/book-save")
                .param("action","save")
                .flashAttr("book", wrongDataBook)
            )
            .andExpect(status().isOk())
            .andExpect(view().name("forms/book-edit"))
            .andExpect(model().attribute("authors",hasSize(2)))
            .andExpect(model().attribute("authors",hasItems(expectedAuthors.get(0),expectedAuthors.get(1))))
            .andExpect(model().attribute("genres",hasSize(2)))
            .andExpect(model().attribute("genres",hasItems(expectedGenres.get(0),expectedGenres.get(1))));
        verify(bookService,times(0))
            .updateBook(wrongDataBook);
    }

    @DisplayName("Должен возвращаться к списку книг")
    @Test
    void shouldStepBackToBookList() throws Exception {
        mockMvc.perform(post("/book-save")
                .param("action","step-back")
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("book-list"));
        verify(bookService,times(0))
            .updateBook(any());
    }

    @DisplayName("Должен удалять книгу по заданному id")
    @Test
    void shouldDeleteBookById() throws Exception {
        final long bookId = 11;
        doNothing()
            .when(bookService)
            .deleteBook(bookId);
        mockMvc.perform(get("/book-delete")
                .param("bookId",String.valueOf(bookId))
            ).andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("book-list"));
        verify(bookService,times(1))
            .deleteBook(bookId);
    }
}
