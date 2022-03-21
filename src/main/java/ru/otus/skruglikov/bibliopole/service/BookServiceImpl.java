package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.dto.BookDTO;
import ru.otus.skruglikov.bibliopole.dto.adapter.BookDTOAdapter;
import ru.otus.skruglikov.bibliopole.exception.BookNotFoundDaoException;
import ru.otus.skruglikov.bibliopole.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional
    public void createBook(final BookDTO bookDTO) {
        final Genre genre = genreService.readById(bookDTO.getGenreId());
        final Author author = authorService.readById(bookDTO.getAuthorId());
        final Book book = BookDTOAdapter.getEntity(bookDTO);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO readBookById(final long bookId) {
        return BookDTOAdapter.getDTO(
            bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundDaoException("не найдена книга с id - " + bookId))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> readAllBooks() {
        return bookRepository.findAll()
            .stream()
            .map( BookDTOAdapter::getDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateBook(final BookDTO bookDTO) {
        final Book book = BookDTOAdapter.getEntity(bookDTO);
        book.setAuthor(bookDTO.getAuthorId() != 0 ? authorService.readById(bookDTO.getAuthorId()) : null);
        book.setGenre(bookDTO.getAuthorId() != 0 ? genreService.readById(bookDTO.getGenreId()) : null);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(final long bookId) {
        bookRepository.deleteById(bookId);
    }
}
