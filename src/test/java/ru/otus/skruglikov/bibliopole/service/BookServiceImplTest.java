package ru.otus.skruglikov.bibliopole.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.repository.BookRepository;
import ru.otus.skruglikov.bibliopole.repository.CommentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("класс BookServiceImpl должен")
@SpringBootTest(classes = BookServiceImpl.class)
public class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;

    @MockBean
    private CommentRepository commentRepository;


    @DisplayName("добавлять новую книгу")
    @Test
    void shouldAddNewBook() {
        final Optional<Genre> newGenre = Optional.of(new Genre(null, "тест жанр"));
        final Optional<Author> newAuthor =
                Optional.of(new Author(null, "тест","тестов","тестович"));
        final Book newBook = new Book(null, "Новая тестовая книга", newGenre.get(), newAuthor.get());
        when(bookRepository.save(any()))
                .thenReturn(newBook);
        when(authorService.readById(eq(newAuthor.get().getId())))
                .thenReturn(newAuthor.get());
        when(genreService.readById(eq(newGenre.get().getId())))
                .thenReturn(newGenre.get());
        bookService.createBook(newBook.getTitle(),newGenre.get().getId(),newAuthor.get().getId());
        verify(bookRepository,times(1))
                .save(newBook);
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        final String bookId = "1";
        doNothing().when(bookRepository).deleteById(bookId);
        bookService.deleteBook(bookId);
        doNothing().when(commentRepository).deleteAllByBookId(bookId);
        verify(bookRepository,times(1))
                .deleteById(bookId);
        verify(commentRepository,times(1))
                .deleteAllByBookId(bookId);
    }

    @DisplayName("обновлять данные по книге")
    @Test
    void shouldUpdateBook() {
        final Genre newGenre = new Genre("1", "тест жанр");
        final Author newAuthor = new Author("1", "тест","тестов","тестович");
        final Book bookForUpdate = new Book("1", "Новая тестовая книга", newGenre, newAuthor);
        when(bookRepository.save(bookForUpdate))
                .thenReturn(bookForUpdate);
        when(authorService.readById(eq(newAuthor.getId())))
                .thenReturn(newAuthor);
        when(genreService.readById(eq(newGenre.getId())))
                .thenReturn(newGenre);
        bookService.updateBook(bookForUpdate.getId(),bookForUpdate.getTitle(),
                bookForUpdate.getGenre().getId(),
                bookForUpdate.getAuthor().getId());
        verify(bookRepository,times(1))
                .save(bookForUpdate);
    }

    @DisplayName("возвращать книгу по id")
    @Test
    void shouldReadBookById() {
        final Genre newGenre = new Genre(null, "тест жанр");
        final Author newAuthor = new Author(null, "тест","тестов","тестович");
        final Book expectedBook = new Book(null, "Новая тестовая книга", newGenre, newAuthor);
        when(bookRepository.findById(eq(expectedBook.getId())))
                .thenReturn(Optional.of(expectedBook));
        assertThat(bookService.readBookById(expectedBook.getId()))
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("возвращать все книги")
    @Test
    void shouldReadAllBooks() {
        final Genre newGenre = new Genre(null, "тест жанр");
        final Author newAuthor = new Author(null, "тест","тестов","тестович");
        final List<Book> expectedBooks = Arrays.asList(new Book(null, "Новая тестовая книга", newGenre, newAuthor),
                new Book("2", "Новая тестовая книга2", newGenre, newAuthor));
        when(bookRepository.findAll())
                .thenReturn(expectedBooks);
        assertThat(bookService.readAllBooks())
                .containsExactlyInAnyOrderElementsOf(expectedBooks);
    }
}
