package ru.otus.skruglikov.bibliopole.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Accessors(chain = true)
public class CommentDTO {
    private long id;
    @NotBlank(message = "пропущен текст комментария")
    private String text;
    @Positive(message = "пропущен идентификатор книги")
    private long bookId;
}
