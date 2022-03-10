package ru.otus.skruglikov.bibliopole.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.domain.Genre;
import ru.otus.skruglikov.bibliopole.repository.CommentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("класс CommentServiceImpl должен")
@SpringBootTest(classes = {CommentServiceImpl.class})
public class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookService bookService;

    @DisplayName("добавляет новый комментарий")
    @Test
    void shouldAddNewComment() {
        final Book testBook = new Book("1","тест",new Genre("1","тест"),
                new Author("1","И","Ф","О"));
        final Comment expectedComment = new Comment(null, "тест комментарий новый",testBook);
        when(commentRepository.save(eq(expectedComment)))
                .thenReturn(expectedComment);
        when(bookService.readBookById(eq(expectedComment.getBook().getId())))
                .thenReturn(testBook);
        commentService.createComment(expectedComment.getText(),expectedComment.getBook().getId());
        verify(commentRepository,times(1))
                .save(expectedComment);
    }

    @DisplayName("обновляет новый комментарий")
    @Test
    void shouldUpdateComment() {
        final Book testBook = new Book("1","тест",new Genre("1","тест"),
                new Author("1","И","Ф","О"));
        final Comment expectedComment = new Comment("1", "тест комментарий новый",testBook);
        when(commentRepository.save(eq(expectedComment)))
                .thenReturn(expectedComment);
        when(bookService.readBookById(eq(expectedComment.getBook().getId())))
                .thenReturn(testBook);
        commentService.updateComment(expectedComment.getId(),expectedComment.getText(),expectedComment.getBook().getId());
        verify(commentRepository,times(1))
                .save(expectedComment);
    }

    @DisplayName("возвращает комментарий по id")
    @Test
    void shouldReadCommentById() {
        final Book testBook = new Book("1","тест",new Genre("1","тест"),
                new Author("1","И","Ф","О"));
        final Comment expectedComment = new Comment("1", "тест комментарий новый",testBook);
        when(commentRepository.findById(eq(expectedComment.getId())))
                .thenReturn(Optional.of(expectedComment));
        final Comment actualComment = commentService.readCommentById(expectedComment.getId());
        assertEquals(expectedComment,actualComment);
    }

    @DisplayName("возвращает все комментарии для книги")
    @Test
    void shouldReadAllCommentsByBookId() {
        final Book testBook = new Book("1","тест",new Genre("1","тест"),
                new Author("1","И","Ф","О"));
        final List<Comment> expectedCommentList = Arrays.asList(new Comment("1", "тест комментарий1",testBook),
                new Comment("2", "тест комменатирй2",testBook));
        when(commentRepository.findALLByBookId(eq(testBook.getId())))
                .thenReturn(expectedCommentList);
        final List<Comment> actualCommentList = commentService.readAllCommentsByBookId(testBook.getId());
        assertThat(actualCommentList)
                .containsExactlyInAnyOrderElementsOf(expectedCommentList);
    }

    @DisplayName("удаляет комментарии по id")
    @Test
    void shouldDeleteCommentsById() {
        final Book testBook = new Book("1","тест",new Genre("1","тест"),
                new Author("1","И","Ф","О"));
        final Comment commentForDelete = new Comment("1", "тест комментарий новый",testBook);
        doNothing()
                .when(commentRepository)
                .deleteById(eq(commentForDelete.getId()));
        commentService.deleteComment(commentForDelete.getId());
        verify(commentRepository,times(1))
                .deleteById(commentForDelete.getId());
    }
}
