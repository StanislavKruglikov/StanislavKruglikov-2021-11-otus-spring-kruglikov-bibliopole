package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.skruglikov.bibliopole.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph(value = "GenreAuthorGraph")
    List<Book> findAll();

    @Override
    @EntityGraph(value = "GenreAuthorGraph")
    Optional<Book> findById(Long aLong);
}
