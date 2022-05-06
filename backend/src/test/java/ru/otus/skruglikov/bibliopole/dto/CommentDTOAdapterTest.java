package ru.otus.skruglikov.bibliopole.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.dto.adapter.CommentDTOAdapter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("класс CommentDTOAdapter должен")
public class CommentDTOAdapterTest {

    @DisplayName("Должен возвращать DTO комментария для заданной сущности")
    @Test
    public void shouldGetCommentDTOByComment() {
        final Book book = new Book(1,null,null,null);
        Comment comment = new Comment(1,"text1",book);
        CommentDTO commentDTO = new CommentDTO()
            .setId(comment.getId())
            .setText(comment.getText())
            .setBookId(book.getId());
        assertThat(commentDTO)
            .isEqualTo(CommentDTOAdapter.getDTO(comment));
    }

    @DisplayName("Должен возвращать сущность комментария по заданному DTO")
    @Test
    public void shouldGetCommentByDTOComment() {
        final Book book = new Book(2,null,null,null);
        Comment comment = new Comment(2,"text2",book);
        final CommentDTO commentDTO = new CommentDTO()
            .setId(comment.getId())
            .setText(comment.getText())
            .setBookId(book.getId());

        assertThat(comment)
            .isEqualTo(CommentDTOAdapter.getEntity(commentDTO));
    }
}
