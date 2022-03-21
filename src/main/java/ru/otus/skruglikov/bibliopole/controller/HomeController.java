package ru.otus.skruglikov.bibliopole.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String getIndex(final String pageName,final Model model) {
        model.addAttribute("activePage", validatePageName(pageName) ? pageName : "");
        return "home";
    }

    private boolean validatePageName(final String pageName) {
        return pageName != null && !pageName.equals("/");
    }

}
