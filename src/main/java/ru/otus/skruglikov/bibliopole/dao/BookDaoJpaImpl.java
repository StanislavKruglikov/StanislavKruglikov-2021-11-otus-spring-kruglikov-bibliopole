package ru.otus.skruglikov.bibliopole.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.exception.BookNotFoundDaoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJpaImpl implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Book> readAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                " join b.genre " +
                " join b.author ", Book.class);
        query.setHint("javax.persistence.fetchgraph",em.getEntityGraph("GenreAuthorGraph"));
        return query.getResultList();
    }

    @Override
    public Book findById(long id) {
        final Book book = em.find(Book.class,id, Map.of("javax.persistence.fetchgraph",
                em.getEntityGraph("GenreAuthorGraph")));
        if(book == null) {
            throw new BookNotFoundDaoException("не найдена книга с id - " + id);
        }
        return book;
    }

    @Override
    public Book save(final Book book) {
        final Book result;
        if(book.getId() != 0) {
            result = em.merge(book);
        } else {
            em.persist(book);
            result = book;
        }
        return result;
    }

    @Override
    public boolean deleteById(final long id) {
        return em.createQuery("delete from Book b where b.id = :id")
                .setParameter("id",id)
                .executeUpdate() == 1;
    }
}
