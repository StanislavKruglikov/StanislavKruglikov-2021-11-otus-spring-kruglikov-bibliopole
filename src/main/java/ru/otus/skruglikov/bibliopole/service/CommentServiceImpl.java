package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.exception.CommentNotFoundDaoException;
import ru.otus.skruglikov.bibliopole.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookService bookService;

    @Override
    @Transactional
    public void createComment(final String text, final String bookId) {
        commentRepository.save(new Comment(null, text, bookService.readBookById(bookId)));
    }

    @Override
    @Transactional
    public void updateComment(final String id, final String text, final String bookId) {
        commentRepository.save(new Comment(id,text, bookService.readBookById(bookId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Comment readCommentById(final String id) {
        return commentRepository
                .findById(id)
                .orElseThrow(()-> new CommentNotFoundDaoException("не найден комментарий с id - " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> readAllCommentsByBookId(final String bookId) {
        return commentRepository.findALLByBookId(bookId);
    }

    @Override
    @Transactional
    public void deleteComment(final String id) {
        commentRepository.deleteById(id);
    }
}
