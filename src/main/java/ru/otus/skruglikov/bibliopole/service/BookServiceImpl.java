package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.exception.BookNotFoundDaoException;
import ru.otus.skruglikov.bibliopole.repository.BookRepository;
import ru.otus.skruglikov.bibliopole.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void createBook(final String bookTitle, final String genreId, final String authorId) {
        final Genre genre = genreService.readById(genreId);
        final Author author = authorService.readById(authorId);
        bookRepository.save(new Book(null, bookTitle, genre, author));
    }

    @Override
    @Transactional(readOnly = true)
    public Book readBookById(final String bookId) {
        return bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundDaoException("не найдена книга с id - " + bookId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void updateBook(final String bookId, final String bookTitle, final String genreId, final String authorId) {
        final Genre genre = genreId != null ? genreService.readById(genreId) : null;
        final Author author = authorId != null ? authorService.readById(authorId) : null;
        bookRepository.save(new Book(bookId, bookTitle, genre, author));
    }

    @Override
    @Transactional
    public void deleteBook(final String bookId) {
        bookRepository.deleteById(bookId);
        commentRepository.deleteAllByBookId(bookId);
    }
}
