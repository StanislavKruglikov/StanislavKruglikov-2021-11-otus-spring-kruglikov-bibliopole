package ru.otus.skruglikov.bibliopole.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.skruglikov.bibliopole.dto.CommentDTO;
import ru.otus.skruglikov.bibliopole.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("comment/{id}")
    public CommentDTO getComment(@PathVariable final long id) {
        return commentService.readCommentById(id);
    }

    @PostMapping("comment")
    public CommentDTO addComment(@RequestBody final CommentDTO commentDTO) {
        return commentService.createComment(commentDTO);
    }

    @PutMapping("comment")
    public CommentDTO saveComment(@RequestBody final CommentDTO commentDTO) {
        return commentService.updateComment(commentDTO);
    }

    @DeleteMapping("comment/{id}")
    public void deleteComment(@PathVariable final long id) {
        commentService.deleteComment(id);
    }
}
