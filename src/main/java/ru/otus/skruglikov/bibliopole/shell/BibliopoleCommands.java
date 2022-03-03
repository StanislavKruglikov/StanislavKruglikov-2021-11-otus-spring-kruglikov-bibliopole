package ru.otus.skruglikov.bibliopole.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.service.AuthorService;
import ru.otus.skruglikov.bibliopole.service.BookService;
import ru.otus.skruglikov.bibliopole.service.CommentService;
import ru.otus.skruglikov.bibliopole.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BibliopoleCommands {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentService commentService;

    @ShellMethod(value = "add book", key = {"ab"})
    public String addBook(String title, long genre, long author) {
        bookService.createBook(title, genre, author);
        return  "\nКнига успешно добавлена";
    }

    @ShellMethod(value = "delete book", key = {"db"})
    public String deleteBook(long id) {
        bookService.deleteBook(id);
        return "\nКнига успешно удалена";
    }

    @ShellMethod(value = "update a book", key = {"ub"})
    public String updateBook(long id,
                             String title,
                             Long genre,
                             Long author) {
        bookService.updateBook(id, title, genre, author);
        return  "\nКнига успешно обновлена";
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

    @ShellMethod(value = "add comment", key = {"ac"})
    public String addComment(String text, long bookId) {
        commentService.createComment(text, bookId);
        return "\nКомментарий успешно добавлен";
    }

    @ShellMethod(value = "update comment", key = {"uc"})
    public String updateComment(long id, String text, long bookId) {
        commentService.updateComment(id, text, bookId);
        return "\nКомментарий успешно обновлен";
    }


    @ShellMethod(value = "delete comment", key = {"dc"})
    public String deleteComment(long id) {
        commentService.deleteComment(id);
        return  "\nКомментарий успешно удален";
    }

    @ShellMethod(value = "read a comment", key = {"rc"})
    public Comment readComment(long id) {
        return commentService.readCommentById(id);
    }

    @ShellMethod(value = "read all book comments", key = {"rabc"})
    public List<Comment> readAllBookComments(long bookId) {
        return commentService.readAllCommentsByBookId(bookId);
    }
}
