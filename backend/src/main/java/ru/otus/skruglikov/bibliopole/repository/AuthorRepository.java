package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.skruglikov.bibliopole.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
