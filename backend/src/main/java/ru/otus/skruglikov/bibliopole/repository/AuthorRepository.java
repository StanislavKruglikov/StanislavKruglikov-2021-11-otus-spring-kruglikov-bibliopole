package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.skruglikov.bibliopole.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
