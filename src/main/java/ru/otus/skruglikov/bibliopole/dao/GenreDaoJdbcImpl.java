package ru.otus.skruglikov.bibliopole.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.exception.GenreNotFoundDaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbcImpl implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;
    private final static GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();

    @Override
    public Genre readById(final long id) {
        final Genre genre;
        try {
            genre = jdbc.queryForObject("select g.id, g.name " +
                            " from genre g " +
                            " where g.id = :id ",
                    Collections.singletonMap("id", id), GENRE_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            throw new GenreNotFoundDaoException("указан не корректный код жанра - " + id);
        }
        return genre;
    }

    @Override
    public List<Genre> readAllGenres() {
        return jdbc.query("select g.id, g.name from genre g ", GENRE_ROW_MAPPER);
    }

    private static class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong("id"), rs.getString("name"));
        }
    }
}
