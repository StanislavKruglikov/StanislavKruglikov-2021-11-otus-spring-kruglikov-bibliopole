package ru.otus.skruglikov.bibliopole.repository;

import ru.otus.skruglikov.bibliopole.domain.Comment;

import java.util.List;

public interface CustomCommentRepository {
    List<Comment> findByBookId(long bookId);
}
