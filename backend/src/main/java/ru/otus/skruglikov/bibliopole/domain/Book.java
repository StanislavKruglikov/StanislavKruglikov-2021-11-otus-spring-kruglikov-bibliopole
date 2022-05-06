package ru.otus.skruglikov.bibliopole.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "GenreAuthorGraph",
        attributeNodes = {@NamedAttributeNode("genre"), @NamedAttributeNode("author")}
)
public class Book {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinColumn(name="genre_id", nullable = false)
    private Genre genre;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinColumn(name="author_id", nullable = false)
    private Author author;
}

