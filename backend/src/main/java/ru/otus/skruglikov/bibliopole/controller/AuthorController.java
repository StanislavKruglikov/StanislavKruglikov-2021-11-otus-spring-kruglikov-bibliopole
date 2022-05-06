package ru.otus.skruglikov.bibliopole.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("author")
    public List<Author> getAuthors() {
        return authorService.readAllAuthors();
    }
}
