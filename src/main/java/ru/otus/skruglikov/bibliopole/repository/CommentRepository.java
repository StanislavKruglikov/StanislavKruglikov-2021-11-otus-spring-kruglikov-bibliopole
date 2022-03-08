package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.skruglikov.bibliopole.domain.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, CustomCommentRepository {

    @Override
    @EntityGraph(value = "BookGenreAuthorGraph")
    Optional<Comment> findById(Long aLong);
}
