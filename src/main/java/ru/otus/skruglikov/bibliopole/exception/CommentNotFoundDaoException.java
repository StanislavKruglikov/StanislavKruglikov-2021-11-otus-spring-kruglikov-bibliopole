package ru.otus.skruglikov.bibliopole.exception;

public class CommentNotFoundDaoException extends RuntimeException {
    public CommentNotFoundDaoException(final String message) {
        super(message);
    }
}
