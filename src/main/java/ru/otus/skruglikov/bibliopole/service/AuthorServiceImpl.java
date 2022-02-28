package ru.otus.skruglikov.bibliopole.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.skruglikov.bibliopole.dao.AuthorDao;
import ru.otus.skruglikov.bibliopole.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    @Transactional(readOnly = true)
    public Author readById(Long authorId) {
        return authorDao.findById(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> readAllAuthors() {
        return authorDao.readAllAuthors();
    }
}
