package ru.otus.skruglikov.bibliopole.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.exception.AuthorNotFoundDaoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJpaImpl implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Author findById(final long id) {
        final Author author = em.find(Author.class, id);
        if(author == null) {
            throw new AuthorNotFoundDaoException("указан не корретный id автора книги - " + id);
        }
        return author;
    }

    @Override
    public List<Author> readAllAuthors() {
        return em.createQuery("select a from Author a", Author.class)
                .getResultList();
    }
}
