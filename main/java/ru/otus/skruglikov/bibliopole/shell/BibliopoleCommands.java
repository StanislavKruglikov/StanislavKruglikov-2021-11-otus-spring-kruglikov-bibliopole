package ru.otus.skruglikov.bibliopole.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.service.AuthorService;
import ru.otus.skruglikov.bibliopole.service.BookService;
import ru.otus.skruglikov.bibliopole.service.GenreService;

import java.util.List;

import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
@RequiredArgsConstructor
public class BibliopoleCommands {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @ShellMethod(value = "add book", key = {"ab"})
    public String addBook(String title, String genre, long author) {
        return bookService.createBook(title, genre, author) ? "\nКнига успешно добавлена" :
                "\nПроизошла ошибка при выполнении комманды";
    }

    @ShellMethod(value = "delete book", key = {"db"})
    public String deleteBook(long id) {
        return bookService.deleteBook(id) ? "\nКнига успешно удалена" : "\nПроизошла ошибка при выполнении комманды";
    }

    @ShellMethod(value = "update a book", key = {"ub"})
    public String updateBook(long id,
                             @ShellOption(defaultValue = NULL) String title,
                             @ShellOption(defaultValue = NULL) String genre,
                             @ShellOption(defaultValue = NULL) Long author) {
        return bookService.updateBook(id,title,genre,author) ? "\nКнига успешно обновлена" :
                "\nПроизошла ошибка при выполнении комманды";
    }

    @ShellMethod(value = "read a book", key = {"rb"})
    public Book readBook(long id) {
        return bookService.readBookById(id);
    }

    @ShellMethod(value = "read all books", key = {"rab"})
    public List<Book> readAllBooks() {
        return bookService.readAllBooks();
    }

    @ShellMethod(value = "read all genres", key = {"rag"})
    public List<Genre> readAllGenres() {
        return genreService.readAllGenres();
    }

    @ShellMethod(value = "read all author", key = {"raa"})
    public List<Author> readAllAuthors() {
        return authorService.readAllAuthors();
    }
}
