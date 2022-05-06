package ru.otus.skruglikov.bibliopole.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("genre")
    public List<Genre> getGenres() {
        return genreService.readAllGenres();
    }
}
