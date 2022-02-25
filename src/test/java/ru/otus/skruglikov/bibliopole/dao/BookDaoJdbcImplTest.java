package ru.otus.skruglikov.bibliopole.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.exception.BookNotFoundDaoException;


import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("класс BookDaoJdbcImpl должен")
@JdbcTest
@Import({BookDaoJdbcImpl.class,GenreDaoJdbcImpl.class,AuthorDaoJdbcImpl.class})
class BookDaoJdbcImplTest {

    @Autowired
    private BookDaoJdbcImpl bookDao;
    @Autowired
    private GenreDaoJdbcImpl genreDao;
    @Autowired
    private AuthorDaoJdbcImpl authorDao;

    @DisplayName("возвращать книгу по указанному id")
    @Test
    void shouldReadGenreById() {
        final Book expectedBook = new Book(1L, "Тестовая книга", genreDao.readById(1L),
                authorDao.readById(1L));
        assertThat(bookDao.readById(1))
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("добавлять книгу")
    @Test
    void shouldAddBook() {
        final Book expectedBook = new Book(3L,"Тестовая книга3", genreDao.readById(2L),
                authorDao.readById(2L));
        bookDao.add(expectedBook);
        assertThat(bookDao.readById(3L))
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);


    }

    @DisplayName("удалять книгу по указанному id")
    @Test
    void shouldDeleteBook() {
        final Book bookBeforeDelete = bookDao.readById(1L);
        assertNotNull(bookBeforeDelete);
        bookDao.deleteById(1L);
        assertThrowsExactly(BookNotFoundDaoException.class,() -> bookDao.readById(1L));
    }

    @DisplayName("обновлять данные по книге")
    @Test
    void shouldUpdateBook() {
        final Book bookToUpdate = new Book(1L,"Тестовая книга новое",genreDao.readById(2L),
                authorDao.readById(2));
        assertThat(bookDao.readById(1L))
                .usingRecursiveComparison()
                .isNotEqualTo(bookToUpdate);
        bookDao.update(bookToUpdate);
        assertThat(bookDao.readById(1L))
                .usingRecursiveComparison()
                .isEqualTo(bookToUpdate);

    }

    @DisplayName("возвращать список всех книг")
    @Test
    void shouldReadAllBooks() {
        assertThat(bookDao.readAll())
                .containsExactlyInAnyOrderElementsOf(List.of(
                                new Book(1L,"Тестовая книга", genreDao.readById(1L), authorDao.readById(1L)),
                                new Book(2L, "Тестовая книга2", genreDao.readById(2L), authorDao.readById(2L))
                        )
                );
    }

    @DisplayName("возвращать ошибку если книга не найдена")
    @Test
    void shouldThrowBookNotFoundException() {
        assertThrowsExactly(BookNotFoundDaoException.class,() -> bookDao.readById(99L));
    }
}