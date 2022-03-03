package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.exception.BookNotFoundDaoException;
import ru.otus.skruglikov.bibliopole.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional
    public void createBook(final String bookTitle, final long genreId, final long authorId) {
        final Genre genre = genreService.readById(genreId);
        final Author author = authorService.readById(authorId);
        bookRepository.save(new Book(0, bookTitle, genre, author));
    }

    @Override
    @Transactional(readOnly = true)
    public Book readBookById(final long bookId) {
        return bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundDaoException("не найдена книга с id - " + bookId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void updateBook(final long bookId, final String bookTitle, final Long genreId, final Long authorId) {
        final Genre genre = genreId != null ? genreService.readById(genreId) : null;
        final Author author = authorId != null ? authorService.readById(authorId) : null;
        bookRepository.save(new Book(bookId, bookTitle, genre, author));
    }

    @Override
    @Transactional
    public void deleteBook(final long bookId) {
        bookRepository.deleteById(bookId);
    }
}
