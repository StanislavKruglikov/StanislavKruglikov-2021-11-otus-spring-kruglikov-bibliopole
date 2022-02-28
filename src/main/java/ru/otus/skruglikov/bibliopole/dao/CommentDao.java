package ru.otus.skruglikov.bibliopole.dao;

import ru.otus.skruglikov.bibliopole.domain.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> findAll();

    Comment findById(long id);

    List<Comment> findAllCommentsByBookId(long bookId);

    Comment save(Comment comment);

    void deleteById(long id);
}
