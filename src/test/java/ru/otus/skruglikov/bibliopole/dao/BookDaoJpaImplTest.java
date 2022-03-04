package ru.otus.skruglikov.bibliopole.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.exception.BookNotFoundDaoException;


import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("класс BookDaoJpaImpl должен")
@DataJpaTest
@Import({BookDaoJpaImpl.class, GenreDaoJpaImpl.class, AuthorDaoJpaImpl.class})
class BookDaoJpaImplTest {

    @Autowired
    private TestEntityManager em;
    @Autowired
    private BookDaoJpaImpl bookDao;
    @Autowired
    private GenreDaoJpaImpl genreDao;
    @Autowired
    private AuthorDaoJpaImpl authorDao;

    @DisplayName("возвращать книгу по указанному id")
    @Test
    void shouldReadGenreById() {
        final Book expectedBook = new Book(1, "Тестовая книга", genreDao.findById(1),
                authorDao.findById(1));
        assertThat(bookDao.findById(1))
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("добавлять книгу")
    @Test
    void shouldAddBook() {
        final Book expectedBook = new Book(0,"Тестовая книга3", genreDao.findById(2),
                authorDao.findById(2));
        bookDao.save(expectedBook);
        assertThat(bookDao.findById(3))
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);


    }

    @DisplayName("удалять книгу по указанному id")
    @Test
    void shouldDeleteBook() {
        final Book bookBeforeDelete = bookDao.findById(1);
        assertNotNull(bookBeforeDelete);
        bookDao.deleteById(1);
        em.detach(bookBeforeDelete);
        assertThrowsExactly(BookNotFoundDaoException.class,() -> bookDao.findById(1));
    }

    @DisplayName("обновлять данные по книге")
    @Test
    void shouldUpdateBook() {
        final Book bookToUpdate = new Book(1,"Тестовая книга новое",genreDao.findById(2),
                authorDao.findById(2));
        assertThat(bookDao.findById(1))
                .usingRecursiveComparison()
                .isNotEqualTo(bookToUpdate);
        bookDao.save(bookToUpdate);
        assertThat(bookDao.findById(1))
                .usingRecursiveComparison()
                .isEqualTo(bookToUpdate);

    }

    @DisplayName("возвращать список всех книг")
    @Test
    void shouldReadAllBooks() {
        assertThat(bookDao.readAll())
                .containsExactlyInAnyOrderElementsOf(List.of(
                                new Book(1,"Тестовая книга", genreDao.findById(1), authorDao.findById(1)),
                                new Book(2, "Тестовая книга2", genreDao.findById(2), authorDao.findById(2))
                        )
                );
    }

    @DisplayName("возвращать ошибку если книга не найдена")
    @Test
    void shouldThrowBookNotFoundException() {
        assertThrowsExactly(BookNotFoundDaoException.class,() -> bookDao.findById(99));
    }
}