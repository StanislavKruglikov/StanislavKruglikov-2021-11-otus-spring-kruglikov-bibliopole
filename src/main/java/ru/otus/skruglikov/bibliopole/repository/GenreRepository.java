package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.skruglikov.bibliopole.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
