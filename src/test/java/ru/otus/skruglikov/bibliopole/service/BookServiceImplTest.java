package ru.otus.skruglikov.bibliopole.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.skruglikov.bibliopole.dao.AuthorDao;
import ru.otus.skruglikov.bibliopole.dao.BookDao;
import ru.otus.skruglikov.bibliopole.dao.GenreDao;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName("класс BookServiceImpl должен")
@SpringBootTest(classes = BookServiceImpl.class)
public class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private BookDao bookDao;
    @MockBean
    private AuthorDao authorDao;
    @MockBean
    private GenreDao genreDao;

    @DisplayName("добавлять новую книгу")
    @Test
    void shouldAddNewBook() {
        final Genre newGenre = new Genre(1, "тест жанр");
        final Author newAuthor = new Author(1, "тест","тестов","тестович");
        final Book newBook = new Book(0, "Новая тестовая книга", newGenre, newAuthor);
        when(bookDao.save(any()))
                .thenReturn(newBook);
        when(authorDao.findById(eq(newAuthor.getId())))
                .thenReturn(newAuthor);
        when(genreDao.findById(eq(newGenre.getId())))
                .thenReturn(newGenre);
        bookService.createBook(newBook.getTitle(),newGenre.getId(),newAuthor.getId());
        verify(bookDao,times(1))
                .save(newBook);
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        final long bookId = 1L;
        when(bookDao.deleteById(bookId))
                .thenReturn(true);
        when(bookDao.findById(bookId))
                .thenReturn(new Book(bookId,null,null,null));
        final boolean result = bookService.deleteBook(bookId);
        assertTrue(result);
        verify(bookDao,times(1))
                .deleteById(bookId);
    }

    @DisplayName("обновлять данные по книге")
    @Test
    void shouldUpdateBook() {
        final Genre newGenre = new Genre(1, "тест жанр");
        final Author newAuthor = new Author(1, "тест","тестов","тестович");
        final Book bookForUpdate = new Book(1L, "Новая тестовая книга", newGenre, newAuthor);
        when(bookDao.save(bookForUpdate))
                .thenReturn(bookForUpdate);
        when(bookDao.findById(bookForUpdate.getId()))
                .thenReturn(bookForUpdate);
        when(authorDao.findById(eq(newAuthor.getId())))
                .thenReturn(newAuthor);
        when(genreDao.findById(eq(newGenre.getId())))
                .thenReturn(newGenre);
        bookService.updateBook(bookForUpdate.getId(),bookForUpdate.getTitle(),
                bookForUpdate.getGenre().getId(),
                bookForUpdate.getAuthor().getId());
        verify(bookDao,times(1))
                .save(bookForUpdate);
    }

    @DisplayName("возвращать книгу по id")
    @Test
    void shouldReadBookById() {
        final Genre newGenre = new Genre(1, "тест жанр");
        final Author newAuthor = new Author(1, "тест","тестов","тестович");
        final Book expectedBook = new Book(1L, "Новая тестовая книга", newGenre, newAuthor);
        when(bookDao.findById(eq(expectedBook.getId())))
                .thenReturn(expectedBook);
        assertThat(bookService.readBookById(expectedBook.getId()))
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("возвращать все книги")
    @Test
    void shouldReadAllBooks() {
        final Genre newGenre = new Genre(1, "тест жанр");
        final Author newAuthor = new Author(1, "тест","тестов","тестович");
        final List<Book> expectedBooks = List.of(new Book(1L, "Новая тестовая книга", newGenre, newAuthor),
                new Book(2L, "Новая тестовая книга2", newGenre, newAuthor));
        when(bookDao.readAll())
                .thenReturn(expectedBooks);
        assertThat(bookService.readAllBooks())
                .containsExactlyInAnyOrderElementsOf(expectedBooks);
    }
}
