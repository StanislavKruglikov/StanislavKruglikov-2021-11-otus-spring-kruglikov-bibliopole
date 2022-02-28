package ru.otus.skruglikov.bibliopole.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.exception.AuthorNotFoundDaoException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@DisplayName("класс AuthorDaoJpaImpl должен")
@DataJpaTest
@Import(AuthorDaoJpaImpl.class)
class AuthorDaoJpaImplTest {

    @Autowired
    private AuthorDaoJpaImpl authorDao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать автора по указанному id")
    @Test
    void shouldReadAuthorById() {
        final Author expectedGenre = new Author(1, "тест","тестов", "тестович");
        assertEquals(expectedGenre,authorDao.findById(1));
    }

    @DisplayName("возвращать список всех авторов")
    @Test
    void shouldReadAllAuthors() {
        final List<Author> expectedAuthorList = List.of(new Author(1, "тест","тестов", "тестович"),
                new Author(2, "тест2","тестов2", "тестович2"));
        assertThat(authorDao.readAllAuthors())
                .containsExactlyInAnyOrderElementsOf(expectedAuthorList);
    }

    @DisplayName("возвращать ошибку если автор не найден")
    @Test
    void shouldThrowAuthorNotFoundException() {
        assertThrowsExactly(AuthorNotFoundDaoException.class,() -> authorDao.findById(99));
    }
}