package ru.otus.skruglikov.bibliopole.service;

import ru.otus.skruglikov.bibliopole.domain.Book;

import java.util.List;

public interface BookService {
    boolean createBook(String bookTitle, String genreCode, long authorId);

    Book readBookById(long bookId);

    List<Book> readAllBooks();

    boolean updateBook(long bookId, String bookTitle, String genreCode, Long authorId);

    boolean deleteBook(long bookId);
}
