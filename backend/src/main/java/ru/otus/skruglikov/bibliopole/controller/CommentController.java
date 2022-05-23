package ru.otus.skruglikov.bibliopole.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.repository.CommentRepository;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;

    @GetMapping("api/comment/{id}")
    public Mono<Comment> getComment(@PathVariable final String id) {
        return commentRepository
                .findById(id);
    }

    @RequestMapping(path = "api/comment", method = {RequestMethod.PUT,RequestMethod.POST})
    public Mono<Comment> saveComment(@RequestBody final Comment comment) {
        return commentRepository.save(comment);
    }

    @DeleteMapping("api/comment/{id}")
    public Mono<Void> deleteComment(@PathVariable final String id) {
        return commentRepository.deleteById(id);
    }

    @GetMapping("api/book/{bookId}/comment")
    public Flux<Comment> getBookComments(@PathVariable final String bookId) {
        return commentRepository.findALLByBookId(bookId);
    }
}
