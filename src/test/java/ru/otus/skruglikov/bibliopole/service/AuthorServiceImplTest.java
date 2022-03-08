package ru.otus.skruglikov.bibliopole.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("класс AuthorServiceImpl должен")
@SpringBootTest(classes = AuthorServiceImpl.class)
public class AuthorServiceImplTest {

    @Autowired
    private AuthorServiceImpl authorService;

    @MockBean
    private AuthorRepository authorRepository;

    @DisplayName("возвращать автора по указанному id")
    @Test
    void shouldReadAuthorById() {
        final Author expectedAuthor = new Author(1, "тест","тестов","тестович");
        when(authorRepository.findById(1L))
                .thenReturn(Optional.of(expectedAuthor));
        assertEquals(expectedAuthor,authorService.readById(1L));
    }

    @DisplayName("возвращать всех авторов")
    @Test
    void shouldReadAllAuthors() {
        final List<Author> expectedAuthorList = List.of(new Author(1, "тест","тестов","тестович"),
                new Author(2, "тест2","тестов2","тестович2"));
        when(authorRepository.findAll())
                .thenReturn(expectedAuthorList);
        assertThat(authorService.readAllAuthors())
                .containsExactlyInAnyOrderElementsOf(expectedAuthorList);
    }
}
