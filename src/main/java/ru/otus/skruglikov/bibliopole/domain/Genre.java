package ru.otus.skruglikov.bibliopole.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "genre")
public class Genre {

    @Id
    private long id;
    private String name;
}
