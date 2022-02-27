package ru.otus.skruglikov.bibliopole.exception;

public class GenreNotFoundDaoException extends RuntimeException {
    public GenreNotFoundDaoException(final String message) {
        super(message);
    }
}
