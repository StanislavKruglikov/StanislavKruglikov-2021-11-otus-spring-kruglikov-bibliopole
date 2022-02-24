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
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbcImpl implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Genre readByCode(final String code) {
        final Genre genre;
        try {
            genre = jdbc.queryForObject("select g.code, g.name " +
                            " from genre g " +
                            " where g.code = :code ",
                    Map.of("code", code), new GenreRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new GenreNotFoundDaoException(code);
        }
        return genre;
    }

    @Override
    public List<Genre> readAllGenres() {
        return jdbc.query("select g.code, g.name from genre g ", new GenreRowMapper());
    }

    private class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getString("code"), rs.getString("name"));
        }
    }
}
