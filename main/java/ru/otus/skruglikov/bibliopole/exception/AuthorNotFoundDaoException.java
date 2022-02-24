package ru.otus.skruglikov.bibliopole.exception;

import lombok.Data;

@Data
public class AuthorNotFoundDaoException extends RuntimeException {
    private final long id;
}
