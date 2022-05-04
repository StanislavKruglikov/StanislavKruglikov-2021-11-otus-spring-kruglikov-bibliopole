package ru.otus.skruglikov.bibliopole.dto.adapter;

import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.dto.CommentDTO;

public class CommentDTOAdapter {
    public static CommentDTO getDTO(final Comment entity) {
        return new CommentDTO()
            .setId(entity.getId())
            .setText(entity.getText())
            .setBookId(entity.getBook().getId());
    }

    public static Comment getEntity(final CommentDTO dto) {
        return new Comment(dto.getId(),dto.getText(),new Book(dto.getBookId(),null,null,null));
    }
}
