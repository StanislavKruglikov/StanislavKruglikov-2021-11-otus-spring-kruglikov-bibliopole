package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.skruglikov.bibliopole.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, Long> {
}
