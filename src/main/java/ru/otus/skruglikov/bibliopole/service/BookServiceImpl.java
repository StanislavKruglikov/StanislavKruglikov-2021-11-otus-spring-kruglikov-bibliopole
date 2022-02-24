package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.skruglikov.bibliopole.dao.BookDao;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.exception.AuthorNotFoundDaoException;
import ru.otus.skruglikov.bibliopole.exception.BookNotFoundDaoException;
import ru.otus.skruglikov.bibliopole.exception.GenreNotFoundDaoException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public boolean createBook(final String bookTitle, final String genreCode, final long authorId) {
        boolean result = false;
        try {
            final Genre genre = genreService.readByCode(genreCode);
            final Author author = authorService.readById(authorId);
            bookDao.add(new Book(null, bookTitle, genre, author));
            result = true;
        } catch (GenreNotFoundDaoException e) {
            System.out.println("\nошибка добавления книги указан не корректный код жанра - " + e.getCode());
        } catch (AuthorNotFoundDaoException e) {
            System.out.println("\nошибка добавления книги указан не корретный id автора книги - " + e.getId());
        } catch (Exception e) {
            System.out.println("\nошибка добавления книги - " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Book readBookById(final long bookId) {
        Book book = null;
        try {
            book = bookDao.readById(bookId);
        } catch (BookNotFoundDaoException e) {
            System.out.println("\nне найдена книга с id - " + e.getId());
        } catch (Exception e) {
            System.out.println("\nошибка чтения книг " + e.getMessage());
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> readAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            books = bookDao.readAll();
        } catch (Exception e) {
            System.out.println("\nошибка чтения книг - " + e.getMessage());
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean updateBook(final long bookId, final String bookTitle, final String genreCode, final Long authorId) {
        boolean result = false;
        try {
            if(readBookById(bookId) != null) {
                final Genre genre = genreCode != null && genreCode.trim().length() > 0 ? genreService.readByCode(genreCode) : null;
                final Author author = authorId != null ? authorService.readById(authorId) : null;
                bookDao.update(new Book(bookId, bookTitle, genre, author));
                result = true;
            }
        } catch (GenreNotFoundDaoException e) {
            System.out.println("\nошибка обновления книги - не найден жанр с кодом - " + e.getCode());
        } catch (AuthorNotFoundDaoException e) {
            System.out.println("\nошибка обновления книги - не найден автор с id - " + e.getId());
        } catch (Exception e) {
            System.out.println("\nошибка обновления книги " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteBook(final long bookId) {
        boolean result = false;
        try {
            if(readBookById(bookId) != null) {
                bookDao.deleteById(bookId);
                result = true;
            }
        } catch (Exception e) {
            System.out.println("\nошибка удаления книги " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
