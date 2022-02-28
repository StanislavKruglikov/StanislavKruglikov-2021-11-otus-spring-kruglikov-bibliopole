package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.skruglikov.bibliopole.dao.BookDao;
import ru.otus.skruglikov.bibliopole.dao.CommentDao;
import ru.otus.skruglikov.bibliopole.domain.Comment;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final BookDao bookDao;

    @Override
    @Transactional
    public void createComment(final String text, final long bookId) {
        commentDao.save(new Comment(0, text, bookDao.findById(bookId)));
    }

    @Override
    @Transactional
    public void updateComment(final long id, final String text, final long bookId) {
        commentDao.save(new Comment(id,text,bookDao.findById(bookId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Comment readCommentById(final long id) {
        return commentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> readAllComments() {
        return commentDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> readAllCommentsByBookId(final long bookId) {
        return commentDao.findAllCommentsByBookId(bookId);
    }

    @Override
    @Transactional
    public void deleteComment(final long id) {
        commentDao.deleteById(id);
    }
}
