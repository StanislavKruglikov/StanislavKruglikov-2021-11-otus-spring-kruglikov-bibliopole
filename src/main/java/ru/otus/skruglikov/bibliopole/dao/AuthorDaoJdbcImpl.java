package ru.otus.skruglikov.bibliopole.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.exception.AuthorNotFoundDaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbcImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Author readById(final long id) {
        final Author author;
        try {
            author = jdbc.queryForObject("select a.id, a.first_name, a.last_name, a.patronymic_name " +
                            " from author a " +
                            " where a.id = :id",
                    Map.of("id", id), new AuthorRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorNotFoundDaoException(id);
        }
        return author;
    }

    @Override
    public List<Author> readAllAuthors() {
        return jdbc.query("select a.id, a.first_name, a.last_name, a.patronymic_name from author a",
                new AuthorRowMapper());
    }

    private class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")
                    , rs.getString("patronymic_name"));
        }
    }
}
