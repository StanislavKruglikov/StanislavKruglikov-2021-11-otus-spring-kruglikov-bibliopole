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
    public void createComment(final String text, final long bookId) {
        commentRepository.save(new Comment(0, text, bookService.readBookById(bookId)));
    }

    @Override
    @Transactional
    public void updateComment(final long id, final String text, final long bookId) {
        commentRepository.save(new Comment(id,text, bookService.readBookById(bookId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Comment readCommentById(final long id) {
        return commentRepository
                .findById(id)
                .orElseThrow(()-> new CommentNotFoundDaoException("не найден комментарий с id - " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> readAllCommentsByBookId(final long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    @Transactional
    public void deleteComment(final long id) {
        commentRepository.deleteById(id);
    }
}
