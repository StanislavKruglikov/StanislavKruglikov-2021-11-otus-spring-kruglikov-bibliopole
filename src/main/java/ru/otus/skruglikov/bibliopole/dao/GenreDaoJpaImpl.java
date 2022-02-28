package ru.otus.skruglikov.bibliopole.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.exception.GenreNotFoundDaoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoJpaImpl implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Genre findById(final long id) {
        final Genre genre = em.find(Genre.class,id);
        if(genre == null) {
            throw new GenreNotFoundDaoException("указан не корректный код жанра - " + id);
        }
        return genre;
    }

    @Override
    public List<Genre> readAllGenres() {
        return em.createQuery("select g from Genre g",Genre.class)
                .getResultList();
    }
}
