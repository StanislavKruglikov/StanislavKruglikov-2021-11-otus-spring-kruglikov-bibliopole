package ru.otus.skruglikov.bibliopole.exception;

import lombok.Data;

@Data
public class GenreNotFoundDaoException extends RuntimeException {
    private final String code;
}
