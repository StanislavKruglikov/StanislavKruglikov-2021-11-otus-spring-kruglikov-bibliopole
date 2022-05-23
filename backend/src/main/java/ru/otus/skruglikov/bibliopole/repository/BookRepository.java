package ru.otus.skruglikov.bibliopole.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.skruglikov.bibliopole.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
