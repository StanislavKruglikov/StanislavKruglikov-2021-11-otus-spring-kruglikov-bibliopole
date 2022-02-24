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
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    @Override
    public void add(final Book book) {
        final Map<String, Object> params = Map.of("title", book.getTitle(),
                "genre_code", book.getGenre().getCode(), "author_id", book.getAuthor().getId());
        jdbc.update("insert into book(title, genre_code, author_id) " +
                "values( :title, :genre_code, :author_id)", params);
    }

    @Override
    public List<Book> readAll() {
        return jdbc.query("select " +
                "b.id," +
                "b.title," +
                "g.code genre_code," +
                "g.name genre_name," +
                "a.id author_id, " +
                "a.first_name author_first_name," +
                "a.last_name author_last_name," +
                "a.patronymic_name author_patronymic_name " +
                " from book b" +
                " join genre g on(g.code = b.genre_code)" +
                " join author a on(a.id = b.author_id) ", new BookRowMapper(true));
    }

    @Override
    public Book readById(long bookId) {
        Book book;
        try {
            book = jdbc.queryForObject("select id,title,genre_code,author_id from book where id = :id", Map.of("id", bookId),
                    new BookRowMapper(false));
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundDaoException(bookId);
        }
        return book;
    }

    @Override
    public void update(final Book book) {
        final Genre genre = book.getGenre();
        final Author author = book.getAuthor();
        final Map<String, Object> params = new HashMap<>() {{
            this.put("id", book.getId());
            this.put("title", book.getTitle());
            this.put("genre_code", genre != null ? genre.getCode() : null);
            this.put("author_id", author != null ? author.getId() : null);
        }};
        jdbc.update("update book " +
                "set title = coalesce(:title,title), " +
                " genre_code = coalesce(:genre_code,genre_code), " +
                " author_id = coalesce(:author_id,author_id) " +
                "where id = :id ", params);
    }

    @Override
    public void deleteById(final long bookId) {
        jdbc.update("delete book where id = :id ", Map.of("id", bookId));
    }

    private class BookRowMapper implements RowMapper<Book> {
        private final boolean isFullRecordSet;

        public BookRowMapper() {
            this(false);
        }

        public BookRowMapper(boolean isFullRecordSet) {
            this.isFullRecordSet = isFullRecordSet;
        }

        @Override
        public Book mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            final Genre genre;
            final Author author;
            if (isFullRecordSet) {
                genre = new Genre(rs.getString("genre_code"), rs.getString("genre_name"));
                author = new Author(rs.getLong("author_id"), rs.getString("author_first_name"),
                        rs.getString("author_last_name"), rs.getString("author_patronymic_name"));
            } else {
                genre = genreDao.readByCode(rs.getString("genre_code"));
                author = authorDao.readById(rs.getLong("author_id"));
            }
            return new Book(rs.getLong("id"), rs.getString("title"), genre, author);
        }
    }
}
