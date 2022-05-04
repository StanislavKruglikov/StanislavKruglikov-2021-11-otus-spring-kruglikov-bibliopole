package ru.otus.skruglikov.bibliopole.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.skruglikov.bibliopole.service.AuthorService;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/author-list")
    public String getAuthorList(final Model model) {
        model.addAttribute("authors",authorService.readAllAuthors());
        return "lists/author-list";
    }
}
