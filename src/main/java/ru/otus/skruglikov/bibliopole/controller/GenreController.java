package ru.otus.skruglikov.bibliopole.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.skruglikov.bibliopole.service.GenreService;

@Controller
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/genre-list")
    public String getGenreList(final Model model) {
        model.addAttribute("genres",genreService.readAllGenres());
        return "genre-list";
    }
}
