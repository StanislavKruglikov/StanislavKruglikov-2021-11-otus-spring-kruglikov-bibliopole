package ru.otus.skruglikov.bibliopole.dto;

import org.junit.jupiter.api.*;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.dto.adapter.BookDTOAdapter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("класс BookDTOAdapter должен")
public class BookDTOAdapterTest {

    @DisplayName("Должен возвращать DTO книги для заданной сущности")
    @Test
    public void shouldGetBookDTOByBook() {
        final Genre genre = new Genre(1,"genre1");
        final Author author = new Author(1,"test11","test12","test13");
        Book book = new Book(1,"title1",genre,author);
        BookDTO bookDTO = new BookDTO()
            .setId(book.getId())
            .setTitle(book.getTitle())
            .setAuthorId(author.getId())
            .setAuthorName(String.format("%s %s %s",author.getLastName(),author.getFirstName(),author.getPatronymicName()))
            .setGenreId(genre.getId())
            .setGenreName(genre.getName());
        assertThat(bookDTO)
            .isEqualTo(BookDTOAdapter.getDTO(book));
    }

    @DisplayName("Должен возвращать сущность книги по заданному DTO")
    @Test
    public void shouldGetBookByDTOBook() {
        final Genre genre = new Genre(2,null);
        final Author author = new Author(2,null,null,null);
        final Book book = new Book(2,"title2",genre,author);
        final BookDTO bookDTO = new BookDTO()
            .setId(book.getId())
            .setTitle(book.getTitle())
            .setAuthorId(author.getId())
            .setAuthorName(null)
            .setGenreId(genre.getId())
            .setGenreName(null);

        assertThat(book)
            .isEqualTo(BookDTOAdapter.getEntity(bookDTO));
    }
}
