package ru.otus.skruglikov.bibliopole.dao;

import ru.otus.skruglikov.bibliopole.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> readAll();

    Book findById(long bookId);

    Book save(Book book);

    boolean deleteById(long bookId);
}
