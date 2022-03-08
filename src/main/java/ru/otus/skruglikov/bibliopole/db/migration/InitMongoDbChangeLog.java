package ru.otus.skruglikov.bibliopole.db.migration;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.google.common.collect.Lists;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.repository.AuthorRepository;
import ru.otus.skruglikov.bibliopole.repository.BookRepository;
import ru.otus.skruglikov.bibliopole.repository.CommentRepository;
import ru.otus.skruglikov.bibliopole.repository.GenreRepository;

import java.util.Arrays;
import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDbChangeLog {

    List<Author> authors;
    List<Genre> genres;
    List<Book> books;

    @ChangeSet(order = "001", id = "dropDb", author = "kruglikov", runAlways = true)
    public void dropDb(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthor", author = "kruglikov", runAlways = true)
    public void insertAuthor(final AuthorRepository authorRepository) {
        authors = Arrays.asList(new Author(null,"Заумнов","Сергей","Сергеевич"),
            new Author(null,"Акабетов","Илья","Петрович"),
            new Author(null,"Пристли","Джон","Бойнтонг"));
        authorRepository.saveAll(authors);
    }

    @ChangeSet(order = "003", id = "insertGenre", author = "kruglikov", runAlways = true)
    public void insertGenre(final GenreRepository repository) {
        genres = Arrays.asList(
            new Genre(null,"триллер"),
            new Genre(null,"детектив"),
            new Genre(null,"научная фантастика")
        );
        repository.saveAll(genres);
    }

    @ChangeSet(order = "004", id = "insertBook", author = "kruglikov", runAlways = true)
    public void insertBook(final BookRepository bookRepository, final AuthorRepository a) {
        books = Arrays.asList(
            new Book(null,"Гонка на выживание",genres.get(0),authors.get(0)),
            new Book(null,"Убийство в темном лесу",genres.get(1),authors.get(0)),
            new Book(null,"Голос со звезды",genres.get(2),authors.get(1)),
            new Book(null,"Чисто английское убийство",genres.get(1),authors.get(2))
        );
        bookRepository.saveAll(books);
    }

    @ChangeSet(order = "005", id = "insertComment", author = "kruglikov", runAlways = true)
    public void insertComment(final CommentRepository commentRepository,final BookRepository bookRepository) {
        final List<Book> books = bookRepository.findAll();
        commentRepository.saveAll(Arrays.asList(
            new Comment(null,"Замечательное произведение, прочел на одном дыхании.",books.get(0)),
            new Comment(null,"Захватывает сюжет.",books.get(0)),
            new Comment(null,"Слегка затянуто, но почитать на досуге можно.",books.get(1))
        ));
    }
}
