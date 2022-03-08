package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.skruglikov.bibliopole.domain.Book;

public interface BookRepository extends MongoRepository<Book, Long> {
}
