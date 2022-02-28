package ru.otus.skruglikov.bibliopole.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
@NamedEntityGraph(
        name = "BookGenreAuthorGraph",
        attributeNodes = { @NamedAttributeNode(value = "book", subgraph = "bookSub") },
        subgraphs = { @NamedSubgraph(
                name = "bookSub", attributeNodes = { @NamedAttributeNode("author"), @NamedAttributeNode("genre") }
        )}
)
public class Comment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    @JoinColumn(name="book_id", nullable = false)
    private Book book;
}
