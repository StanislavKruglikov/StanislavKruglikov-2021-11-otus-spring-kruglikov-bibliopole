package ru.otus.skruglikov.bibliopole.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.exception.CommentNotFoundDaoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CommentDaoJpaImpl implements CommentDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Comment save(final Comment comment) {
        final Comment result;
        if(comment.getId() != 0) {
            result = em.merge(comment);
        } else {
            em.persist(comment);
            result = comment;
        }
        return result;
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c",Comment.class);
        query.setHint("javax.persistence.fetchgraph",em.getEntityGraph("BookGenreAuthorGraph"));
        return query.getResultList();
    }

    @Override
    public List<Comment> findAllCommentsByBookId(final long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :bookId",Comment.class);
        query.setHint("javax.persistence.fetchgraph",em.getEntityGraph("BookGenreAuthorGraph"));
        return query
                .setParameter("bookId",bookId)
                .getResultList();
    }

    @Override
    public Comment findById(final long id) {
        final Comment comment = em.find(Comment.class, id,
                Map.of("javax.persistence.fetchgraph",em.getEntityGraph("BookGenreAuthorGraph"))
        );
        if(comment == null) {
            throw new CommentNotFoundDaoException("не найден комментарий с id - " + id);
        }
        return comment;
    }

    @Override
    public void deleteById(final long id) {
        final int deletedRows = em.createQuery("delete from Comment c where c.id = :id")
                .setParameter("id",id)
                .executeUpdate();
        if(deletedRows != 1) {
            throw new CommentNotFoundDaoException("не найден комментарий с id - " + id);
        }
    }
}
