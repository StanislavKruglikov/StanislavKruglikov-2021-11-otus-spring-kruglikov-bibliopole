package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.skruglikov.bibliopole.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, Long> {
}
