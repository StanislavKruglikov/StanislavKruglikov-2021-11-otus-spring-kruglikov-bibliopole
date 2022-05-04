package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.dto.CommentDTO;
import ru.otus.skruglikov.bibliopole.dto.adapter.CommentDTOAdapter;
import ru.otus.skruglikov.bibliopole.exception.CommentNotFoundDaoException;
import ru.otus.skruglikov.bibliopole.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void createComment(final CommentDTO commentDTO) {
        commentRepository.save(CommentDTOAdapter.getEntity(commentDTO));
    }

    @Override
    @Transactional
    public void updateComment(final CommentDTO commentDTO) {
        commentRepository.save(CommentDTOAdapter.getEntity(commentDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDTO readCommentById(final long id) {
        return CommentDTOAdapter.getDTO(commentRepository
                .findById(id)
                .orElseThrow(()-> new CommentNotFoundDaoException("не найден комментарий с id - " + id))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> readAllCommentsByBookId(final long bookId) {
        return commentRepository.findByBookId(bookId)
            .stream()
            .map(CommentDTOAdapter::getDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteComment(final long commentId) {
        commentRepository.deleteById(commentId);
    }
}
