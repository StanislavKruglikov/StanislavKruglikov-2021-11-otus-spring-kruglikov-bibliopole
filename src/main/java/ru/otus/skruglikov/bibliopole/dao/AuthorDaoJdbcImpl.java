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
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbcImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;
    private final static AuthorRowMapper AUTHOR_ROW_MAPPER = new AuthorRowMapper();

    @Override
    public Author readById(final long id) {
        final Author author;
        try {
            author = jdbc.queryForObject("select a.id, a.first_name, a.last_name, a.patronymic_name " +
                            " from author a " +
                            " where a.id = :id",
                    Collections.singletonMap("id",id), AUTHOR_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorNotFoundDaoException("указан не корретный id автора книги - " + id);
        }
        return author;
    }

    @Override
    public List<Author> readAllAuthors() {
        return jdbc.query("select a.id, a.first_name, a.last_name, a.patronymic_name from author a",
                AUTHOR_ROW_MAPPER);
    }

    private static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")
                    , rs.getString("patronymic_name"));
        }
    }
}
