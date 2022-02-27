package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.skruglikov.bibliopole.dao.AuthorDao;
import ru.otus.skruglikov.bibliopole.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public Author readById(Long authorId) {
        return authorDao.readById(authorId);
    }

    @Override
    public List<Author> readAllAuthors() {
        return authorDao.readAllAuthors();
    }
}
