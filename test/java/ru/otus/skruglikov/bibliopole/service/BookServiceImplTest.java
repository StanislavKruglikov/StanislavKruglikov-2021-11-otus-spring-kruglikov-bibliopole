package ru.otus.skruglikov.bibliopole.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.skruglikov.bibliopole.dao.BookDao;
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
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;

    @DisplayName("добавлять новую книгу")
    @Test
    void shouldAddNewBook() {
        final Genre newGenre = new Genre("test_code", "тест жанр");
        final Author newAuthor = new Author(1L, "тест","тестов","тестович");
        final Book newBook = new Book(null, "Новая тестовая книга", newGenre, newAuthor);
        doNothing()
                .when(bookDao)
                .add(any());
        when(authorService.readById(eq(newAuthor.getId())))
                .thenReturn(newAuthor);
        when(genreService.readByCode(eq(newGenre.getCode())))
                .thenReturn(newGenre);
        final boolean result = bookService.createBook(newBook.getTitle(),newGenre.getCode(),newAuthor.getId());
        assertTrue(result);
        verify(bookDao,times(1)).add(newBook);
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        final long bookId = 1L;
        doNothing()
                .when(bookDao)
                .add(any());
        when(bookDao.readById(bookId))
                .thenReturn(new Book(bookId,null,null,null));
        final boolean result = bookService.deleteBook(bookId);
        assertTrue(result);
        verify(bookDao,times(1))
                .deleteById(bookId);
    }

    @DisplayName("обновлять данные по книге")
    @Test
    void shouldUpdateBook() {
        final Genre newGenre = new Genre("test_code", "тест жанр");
        final Author newAuthor = new Author(1L, "тест","тестов","тестович");
        final Book bookForUpdate = new Book(1L, "Новая тестовая книга", newGenre, newAuthor);
        doNothing()
                .when(bookDao)
                .add(any());
        when(bookDao.readById(bookForUpdate.getId()))
                .thenReturn(bookForUpdate);
        when(authorService.readById(eq(newAuthor.getId())))
                .thenReturn(newAuthor);
        when(genreService.readByCode(eq(newGenre.getCode())))
                .thenReturn(newGenre);
        final boolean result = bookService.updateBook(bookForUpdate.getId(),bookForUpdate.getTitle(),
                bookForUpdate.getGenre().getCode(),
                bookForUpdate.getAuthor().getId());
        assertTrue(result);
        verify(bookDao,times(1)).update(bookForUpdate);
    }

    @DisplayName("возвращать книгу по id")
    @Test
    void shouldReadBookById() {
        final Genre newGenre = new Genre("test_code", "тест жанр");
        final Author newAuthor = new Author(1L, "тест","тестов","тестович");
        final Book expectedBook = new Book(1L, "Новая тестовая книга", newGenre, newAuthor);
        when(bookDao.readById(eq(expectedBook.getId())))
                .thenReturn(expectedBook);
        assertThat(bookService.readBookById(expectedBook.getId()))
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("возвращать все книги")
    @Test
    void shouldReadAllBooks() {
        final Genre newGenre = new Genre("test_code", "тест жанр");
        final Author newAuthor = new Author(1L, "тест","тестов","тестович");
        final List<Book> expectedBooks = List.of(new Book(1L, "Новая тестовая книга", newGenre, newAuthor),
                new Book(2L, "Новая тестовая книга2", newGenre, newAuthor));
        when(bookDao.readAll())
                .thenReturn(expectedBooks);
        assertThat(bookService.readAllBooks())
                .containsExactlyInAnyOrderElementsOf(expectedBooks);
    }
}
