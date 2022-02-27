package ru.otus.skruglikov.bibliopole.exception;

public class BookNotFoundDaoException extends RuntimeException {
    public BookNotFoundDaoException(final String message) {
        super(message);
    }
}
