package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.skruglikov.bibliopole.domain.Comment;

import java.util.List;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
    Flux<Comment> findALLByBookId(String bookId);

    void deleteAllByBookId(String bookId);
}
