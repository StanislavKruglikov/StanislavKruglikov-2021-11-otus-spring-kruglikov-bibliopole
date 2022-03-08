package ru.otus.skruglikov.bibliopole.service;

import ru.otus.skruglikov.bibliopole.domain.Book;

import java.util.List;

public interface BookService {
    void createBook(String bookTitle, String genreId, String authorId);

    Book readBookById(String bookId);

    List<Book> readAllBooks();

    void updateBook(String bookId, String bookTitle, String genreId, String authorId);

    void deleteBook(String bookId);
}
