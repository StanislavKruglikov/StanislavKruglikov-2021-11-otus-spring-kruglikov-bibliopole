package ru.otus.skruglikov.bibliopole.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.service.AuthorService;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("класс AuthorController должен")
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Должен возвращать список авторов")
    @Test
    void shouldGetAuthorList() throws Exception {
        final List<Author> expectedAuthors = List.of(
            new Author(1,"test11","test12","test13"),
            new Author(2,"test21","test22","test23")
        );
        when(authorService.readAllAuthors())
            .thenReturn(expectedAuthors);

        mockMvc.perform(get("/author"))
            .andExpect(status().isOk());
        verify(authorService,times(1))
            .readAllAuthors();
    }

}
