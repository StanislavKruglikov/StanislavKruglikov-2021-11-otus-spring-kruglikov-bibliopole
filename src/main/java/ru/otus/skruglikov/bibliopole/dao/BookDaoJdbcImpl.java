package ru.otus.skruglikov.bibliopole.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.exception.BookNotFoundDaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbcImpl implements BookDao {

    private final NamedParameterJdbcOperations jdbc;
    private final static BookRowMapper BOOK_ROW_MAPPER = new BookRowMapper();

    @Override
    public void add(final Book book) {
        final Map<String, Object> params = Map.of("title", book.getTitle(),
                "genre_id", book.getGenre().getId(), "author_id", book.getAuthor().getId());
        jdbc.update("insert into book(title, genre_id, author_id) " +
                "values( :title, :genre_id, :author_id)", params);
    }

    @Override
    public List<Book> readAll() {
        return jdbc.query("select " +
                "b.id," +
                "b.title," +
                "g.id genre_id," +
                "g.name genre_name," +
                "a.id author_id, " +
                "a.first_name author_first_name," +
                "a.last_name author_last_name," +
                "a.patronymic_name author_patronymic_name " +
                " from book b" +
                " inner join genre g on(g.id = b.genre_id)" +
                " inner join author a on(a.id = b.author_id) ", BOOK_ROW_MAPPER);
    }

    @Override
    public Book readById(long bookId) {
        Book book;
        try {
            book = jdbc.queryForObject("select " +
                            "b.id," +
                            "b.title," +
                            "g.id genre_id," +
                            "g.name genre_name," +
                            "a.id author_id, " +
                            "a.first_name author_first_name," +
                            "a.last_name author_last_name," +
                            "a.patronymic_name author_patronymic_name " +
                            " from book b" +
                            "  inner join genre g on(g.id = b.genre_id)" +
                            "  inner join author a on(a.id = b.author_id) " +
                            " where b.id = :id", Map.of("id", bookId),
                    BOOK_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundDaoException("не найдена книга с id - " + bookId);
        }
        return book;
    }

    @Override
    public boolean update(final Book book) {
        final Genre genre = book.getGenre();
        final Author author = book.getAuthor();
        final Map<String, Object> params = new HashMap<>() {{
            this.put("id", book.getId());
            this.put("title", book.getTitle());
            this.put("genre_id", genre != null ? genre.getId() : null);
            this.put("author_id", author != null ? author.getId() : null);
        }};
        return jdbc.update("update book " +
                "set title = coalesce(:title,title), " +
                " genre_id = coalesce(:genre_id,genre_id), " +
                " author_id = coalesce(:author_id,author_id) " +
                "where id = :id ", params) == 1;
    }

    @Override
    public boolean deleteById(final long bookId) {
        return jdbc.update("delete book where id = :id ", Map.of("id", bookId)) == 1;
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            final Genre genre = new Genre(rs.getLong("genre_id"), rs.getString("genre_name"));
            final Author author = new Author(rs.getLong("author_id"), rs.getString("author_first_name"),
                    rs.getString("author_last_name"), rs.getString("author_patronymic_name"));
            return new Book(rs.getLong("id"), rs.getString("title"), genre, author);
        }
    }
}
