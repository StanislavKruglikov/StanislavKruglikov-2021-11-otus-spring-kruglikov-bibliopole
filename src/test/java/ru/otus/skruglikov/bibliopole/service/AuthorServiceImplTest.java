package ru.otus.skruglikov.bibliopole.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.skruglikov.bibliopole.dao.AuthorDao;
import ru.otus.skruglikov.bibliopole.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("класс AuthorServiceImpl должен")
@SpringBootTest(classes = AuthorServiceImpl.class)
public class AuthorServiceImplTest {

    @Autowired
    private AuthorServiceImpl authorService;

    @MockBean
    private AuthorDao authorDao;

    @DisplayName("возвращать автора по указанному id")
    @Test
    void shouldReadAuthorById() {
        final Author expectedAuthor = new Author(1, "тест","тестов","тестович");
        when(authorDao.findById(1L))
                .thenReturn(expectedAuthor);
        assertEquals(expectedAuthor,authorService.readById(1L));
    }

    @DisplayName("возвращать всех авторов")
    @Test
    void shouldReadAllAuthors() {
        final List<Author> expectedAuthorList = List.of(new Author(1, "тест","тестов","тестович"),
                new Author(2, "тест2","тестов2","тестович2"));
        when(authorDao.readAllAuthors())
                .thenReturn(expectedAuthorList);
        assertThat(authorService.readAllAuthors())
                .containsExactlyInAnyOrderElementsOf(expectedAuthorList);
    }
}
