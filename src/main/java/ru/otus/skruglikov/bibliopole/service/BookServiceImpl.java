package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.skruglikov.bibliopole.dao.AuthorDao;
import ru.otus.skruglikov.bibliopole.dao.BookDao;
import ru.otus.skruglikov.bibliopole.dao.GenreDao;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    @Transactional
    public void createBook(final String bookTitle, final long genreId, final long authorId) {
        final Genre genre = genreDao.findById(genreId);
        final Author author = authorDao.findById(authorId);
        bookDao.save(new Book(0, bookTitle, genre, author));
    }

    @Override
    @Transactional(readOnly = true)
    public Book readBookById(final long bookId) {
        return bookDao.findById(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> readAllBooks() {
        return bookDao.readAll();
    }

    @Override
    @Transactional
    public void updateBook(final long bookId, final String bookTitle, final Long genreId, final Long authorId) {
        final Genre genre = genreId != null ? genreDao.findById(genreId) : null;
        final Author author = authorId != null ? authorDao.findById(authorId) : null;
        bookDao.save(new Book(bookId, bookTitle, genre, author));
    }

    @Override
    @Transactional
    public boolean deleteBook(final long bookId) {
        return bookDao.deleteById(bookId);
    }
}
