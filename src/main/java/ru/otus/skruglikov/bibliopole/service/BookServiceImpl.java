package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.skruglikov.bibliopole.dao.BookDao;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public void createBook(final String bookTitle, final long genreId, final long authorId) {
        final Genre genre = genreService.readById(genreId);
        final Author author = authorService.readById(authorId);
        bookDao.add(new Book(null, bookTitle, genre, author));
    }

    @Override
    public Book readBookById(final long bookId) {
        return bookDao.readById(bookId);
    }

    @Override
    public List<Book> readAllBooks() {
        return bookDao.readAll();
    }

    @Override
    public boolean updateBook(final long bookId, final String bookTitle, final Long genreId, final Long authorId) {
        final Genre genre = genreId != null ? genreService.readById(genreId) : null;
        final Author author = authorId != null ? authorService.readById(authorId) : null;
        return bookDao.update(new Book(bookId, bookTitle, genre, author));
    }

    @Override
    public boolean deleteBook(final long bookId) {
        return bookDao.deleteById(bookId);
    }
}
