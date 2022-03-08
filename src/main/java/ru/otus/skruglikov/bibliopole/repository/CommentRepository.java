package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.skruglikov.bibliopole.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findALLByBookId(String bookId);

    void deleteAllByBookId(String bookId);
}
