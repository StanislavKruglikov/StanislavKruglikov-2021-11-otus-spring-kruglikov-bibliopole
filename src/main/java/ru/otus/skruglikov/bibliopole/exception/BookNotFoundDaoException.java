package ru.otus.skruglikov.bibliopole.exception;

import lombok.Data;

@Data
public class BookNotFoundDaoException extends RuntimeException {
    private final long id;
}
