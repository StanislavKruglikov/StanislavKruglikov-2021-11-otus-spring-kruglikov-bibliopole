package ru.otus.skruglikov.bibliopole.service;

import ru.otus.skruglikov.bibliopole.domain.Comment;

import java.util.List;

public interface CommentService {
    void createComment(String text, long bookId);

    void updateComment(long id, String text, long bookId);

    Comment readCommentById(long id);

    List<Comment> readAllCommentsByBookId(long bookId);

    void deleteComment(long bookId);
}
