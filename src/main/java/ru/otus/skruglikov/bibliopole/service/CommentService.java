package ru.otus.skruglikov.bibliopole.service;

import ru.otus.skruglikov.bibliopole.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    void createComment(CommentDTO commentDTO);

    void updateComment(CommentDTO commentDTO);

    CommentDTO readCommentById(long id);

    List<CommentDTO> readAllCommentsByBookId(long bookId);

    void deleteComment(long commentId);
}
