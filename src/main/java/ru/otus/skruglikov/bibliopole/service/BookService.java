package ru.otus.skruglikov.bibliopole.service;

import ru.otus.skruglikov.bibliopole.domain.Book;

import java.util.List;

public interface BookService {
    void createBook(String bookTitle, long genreId, long authorId);

    Book readBookById(long bookId);

    List<Book> readAllBooks();

    boolean updateBook(long bookId, String bookTitle, Long genreId, Long authorId);

    boolean deleteBook(long bookId);
}
