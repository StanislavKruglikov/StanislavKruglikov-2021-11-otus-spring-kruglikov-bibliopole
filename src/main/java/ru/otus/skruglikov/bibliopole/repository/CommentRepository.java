package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.skruglikov.bibliopole.domain.Comment;

import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, Long>, CustomCommentRepository {

    @Override
    Optional<Comment> findById(Long aLong);
}
