package ru.otus.skruglikov.bibliopole.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.skruglikov.bibliopole.dto.BookDTO;
import ru.otus.skruglikov.bibliopole.service.AuthorService;
import ru.otus.skruglikov.bibliopole.service.BookService;
import ru.otus.skruglikov.bibliopole.service.GenreService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("book-list")
    public String getBookList(final Model model) {
        model.addAttribute("books",bookService.readAllBooks());
        return "lists/book-list";
    }

    @GetMapping("book-edit")
    public String getBookEdit(ModelMap model,
                              @Positive(message = "Ошибочный идентификатор книги") final long bookId) {
        model.addAttribute("book", bookId > 0 ? bookService.readBookById(bookId) : new BookDTO());
        model.addAttribute("authors",authorService.readAllAuthors());
        model.addAttribute("genres",genreService.readAllGenres());
        return "forms/book-edit";
    }

    @PostMapping("book-save")
    public String saveBook(ModelMap model,
                           @Pattern(regexp="^(save|cancel)$",message = "Ошибочное действие") String action,
                           @Valid @ModelAttribute("book") final BookDTO book,
                           BindingResult bindingResult
                           ) {
        String resultView = "redirect:book-list";
        if("save".equals(action)) {
            if(bindingResult.hasErrors()) {
                model.addAttribute("authors",authorService.readAllAuthors());
                model.addAttribute("genres",genreService.readAllGenres());
                resultView = "forms/book-edit";
            } else {
                bookService.updateBook(book);
            }
        }
        return resultView;
    }

    @GetMapping("book-delete")
    public String deleteBook(@Positive(message = "Ошибочный идентификатор книги") final long bookId) {
        bookService.deleteBook(bookId);
        return "redirect:book-list";
    }

}
