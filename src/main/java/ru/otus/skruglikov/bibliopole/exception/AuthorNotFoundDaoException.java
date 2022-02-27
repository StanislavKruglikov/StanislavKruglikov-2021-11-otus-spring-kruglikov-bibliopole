package ru.otus.skruglikov.bibliopole.exception;


public class AuthorNotFoundDaoException extends RuntimeException {
    public AuthorNotFoundDaoException(final String message) {
        super(message);
    }
}
