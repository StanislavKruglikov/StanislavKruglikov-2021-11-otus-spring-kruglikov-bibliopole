package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.skruglikov.bibliopole.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
}
