package ru.otus.skruglikov.bibliopole.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("класс CustomCommentRepository должен")
public class CustomCommentRepositoryTest {
    @Autowired
    private CommentRepository customCommentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Должен находить все комментарии с заданым id книги")
    @Test
    void shouldFindByBookId() {
        final Book testBook = bookRepository.getById(1L);
        assertThat(customCommentRepository.findByBookId(1))
                .containsExactlyInAnyOrder(new Comment(1,"тест комментарий1", testBook),
                        new Comment(2,"тест комментарий2", testBook));
    }

}
