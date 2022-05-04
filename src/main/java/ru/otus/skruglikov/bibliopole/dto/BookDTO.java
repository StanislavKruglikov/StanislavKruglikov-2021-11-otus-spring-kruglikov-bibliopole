package ru.otus.skruglikov.bibliopole.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Accessors(chain = true)
public class BookDTO {
    private long id;
    @NotBlank(message = "пропущено название")
    private String title;
    @Positive(message = "пропущен жанр")
    private long genreId;
    private String genreName;
    @Positive(message = "пропущен автор")
    private long authorId;
    private String authorName;
}
