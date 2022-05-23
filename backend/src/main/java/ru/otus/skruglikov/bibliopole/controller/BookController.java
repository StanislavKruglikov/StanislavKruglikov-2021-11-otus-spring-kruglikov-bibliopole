package ru.otus.skruglikov.bibliopole.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.repository.AuthorRepository;
import ru.otus.skruglikov.bibliopole.repository.BookRepository;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping("api/book")
    public Flux<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("api/book/{id}")
    public Mono<Book> getBook(@PathVariable final String id) {
        return bookRepository.findById(id);
    }

    @RequestMapping(path = "api/book",method = {RequestMethod.POST,RequestMethod.PUT})
    public Mono<Book> saveBook(@RequestBody final Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("api/book/{id}")
    public Mono<Void> deleteBook(@PathVariable final String id) {
        return bookRepository.deleteById(id);
    }

}
