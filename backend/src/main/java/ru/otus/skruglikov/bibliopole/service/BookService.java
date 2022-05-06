package ru.otus.skruglikov.bibliopole.service;

import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);

    BookDTO readBookById(long bookId);

    List<BookDTO> readAllBooks();

    BookDTO updateBook(BookDTO bookDTO);

    void deleteBook(long bookId);
}
