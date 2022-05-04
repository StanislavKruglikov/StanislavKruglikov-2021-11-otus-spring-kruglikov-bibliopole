package ru.otus.skruglikov.bibliopole.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.skruglikov.bibliopole.dto.CommentDTO;
import ru.otus.skruglikov.bibliopole.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("comment-list")
    public String getCommentList(ModelMap model,
                              @Positive(message = "Ошибочный идентификатор книги") final long bookId) {
            model.addAttribute("comments", commentService.readAllCommentsByBookId(bookId));
            model.addAttribute("bookId", bookId);
        return "lists/comment-list";
    }

    @GetMapping("comment-edit")
    public String getCommentEdit(ModelMap model,
                              @Pattern(regexp="^(edit|step-back)$",message = "Ошибочное действие") String action,
                              @Positive(message = "Ошибочный идентификатор комментария") final long commentId,
                              @Min(value = 1, message = "Ошибочный идентификатор книги") final long bookId) {
        String resultView = "forms/comment-edit";
        if("step-back".equals(action)) {
            resultView = "redirect:book-list";
        } else {
            model.addAttribute("comment", commentId > 0 ? commentService.readCommentById(commentId) :
                new CommentDTO()
                    .setBookId(bookId)
            );
        }
        return resultView;
    }

    @PostMapping("comment-save")
    public String saveComment(ModelMap model,
                           @Pattern(regexp="^(save|cancel)$",message = "Ошибочное действие") String action,
                           @Valid @ModelAttribute("comment") final CommentDTO commentDTO,
                           BindingResult bindingResult
                           ) {
        String resultView = getRedirectToList(commentDTO.getBookId());
        if("save".equals(action)) {
            if(bindingResult.hasErrors()) {
                resultView = "forms/comment-edit";
            } else {
                commentService.updateComment(commentDTO);
            }
        }
        return resultView;
    }

    @GetMapping("comment-delete")
    public String deleteComment(@Positive(message = "Ошибочный идентификатор комментария") final long commentId,
                                @Min(value = 1, message = "Ошибочный идентификатор книги") final long bookId) {
        commentService.deleteComment(commentId);
        return getRedirectToList(bookId);
    }

    private String getRedirectToList(final long bookId) {
        return "redirect:comment-list?bookId="+bookId;
    }
}
