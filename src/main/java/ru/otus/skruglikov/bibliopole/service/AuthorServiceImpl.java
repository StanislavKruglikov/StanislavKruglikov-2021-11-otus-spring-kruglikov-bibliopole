package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.exception.AuthorNotFoundDaoException;
import ru.otus.skruglikov.bibliopole.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public Author readById(final String authorId) {
        return authorRepository
                .findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundDaoException("указан не корретный id автора книги - " + authorId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> readAllAuthors() {
        return authorRepository.findAll();
    }
}
