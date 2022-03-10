package ru.otus.skruglikov.bibliopole.service;

import ru.otus.skruglikov.bibliopole.domain.Comment;

import java.util.List;

public interface CommentService {
    void createComment(String text, String bookId);

    void updateComment(String id, String text, String bookId);

    Comment readCommentById(String id);

    List<Comment> readAllCommentsByBookId(String bookId);

    void deleteComment(String bookId);
}
