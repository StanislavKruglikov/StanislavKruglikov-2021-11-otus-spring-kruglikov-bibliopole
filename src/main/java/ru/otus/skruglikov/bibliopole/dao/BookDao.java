package ru.otus.skruglikov.bibliopole.dao;

import ru.otus.skruglikov.bibliopole.domain.Book;

import java.util.List;

public interface BookDao {

    void add(Book book);

    List<Book> readAll();

    Book readById(long bookId);

    boolean update(Book book);

    boolean deleteById(long bookId);
}
