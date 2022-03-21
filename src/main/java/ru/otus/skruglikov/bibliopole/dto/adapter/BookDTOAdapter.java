package ru.otus.skruglikov.bibliopole.dto.adapter;

import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.dto.BookDTO;

public class BookDTOAdapter {
    public static BookDTO getDTO(final Book entity) {
        return new BookDTO()
            .setId(entity.getId())
            .setTitle(entity.getTitle())
            .setAuthorId(entity.getAuthor().getId())
            .setGenreId(entity.getGenre().getId());
    }

    public static Book getEntity(final BookDTO dto) {
        return new Book(dto.getId(),dto.getTitle(),new Genre(dto.getGenreId(),null),
            new Author(dto.getAuthorId(),null,null,null));
    }
}
