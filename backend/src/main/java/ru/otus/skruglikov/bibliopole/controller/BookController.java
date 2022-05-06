package ru.otus.skruglikov.bibliopole.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.skruglikov.bibliopole.dto.BookDTO;
import ru.otus.skruglikov.bibliopole.dto.CommentDTO;
import ru.otus.skruglikov.bibliopole.service.BookService;
import ru.otus.skruglikov.bibliopole.service.CommentService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CommentService commentService;

    @GetMapping("book")
    public List<BookDTO> getBooks() {
        return bookService.readAllBooks();
    }

    @GetMapping("book/{id}")
    public BookDTO getBook(@PathVariable final long id) {
        return bookService.readBookById(id);
    }

    @PostMapping("book")
    public BookDTO addBook(@RequestBody final BookDTO book) {
        return bookService.createBook(book);
    }

    @PutMapping("book")
    public BookDTO saveBook(@RequestBody final BookDTO book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("book/{id}")
    public void deleteBook(@PathVariable final long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("book/{bookId}/comment")
    public List<CommentDTO> getBookComments(@PathVariable final long bookId) {
        return commentService.readAllCommentsByBookId(bookId);
    }
}
