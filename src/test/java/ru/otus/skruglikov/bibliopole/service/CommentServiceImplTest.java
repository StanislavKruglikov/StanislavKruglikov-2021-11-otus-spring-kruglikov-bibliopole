package ru.otus.skruglikov.bibliopole.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.skruglikov.bibliopole.dao.AuthorDao;
import ru.otus.skruglikov.bibliopole.dao.BookDao;
import ru.otus.skruglikov.bibliopole.dao.CommentDao;
import ru.otus.skruglikov.bibliopole.dao.GenreDao;
import ru.otus.skruglikov.bibliopole.domain.Author;
import ru.otus.skruglikov.bibliopole.domain.Book;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName("класс CommentServiceImpl должен")
@SpringBootTest(classes = CommentServiceImpl.class)
public class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @MockBean
    private CommentDao commentDao;

    @MockBean
    private BookDao bookDao;

    @DisplayName("добавляет новый комментарий")
    @Test
    void shouldAddNewComment() {
        final Book testBook = new Book(1,"тест",new Genre(1,"тест"),
                new Author(1,"И","Ф","О"));
        final Comment expectedComment = new Comment(0, "тест комментарий новый",testBook);
        when(commentDao.save(eq(expectedComment)))
                .thenReturn(expectedComment);
        when(bookDao.findById(eq(expectedComment.getBook().getId())))
                .thenReturn(testBook);
        commentService.createComment(expectedComment.getText(),expectedComment.getBook().getId());
        verify(commentDao,times(1))
                .save(expectedComment);
    }

    @DisplayName("обновляет новый комментарий")
    @Test
    void shouldUpdateComment() {
        final Book testBook = new Book(1,"тест",new Genre(1,"тест"),
                new Author(1,"И","Ф","О"));
        final Comment expectedComment = new Comment(1, "тест комментарий новый",testBook);
        when(commentDao.save(eq(expectedComment)))
                .thenReturn(expectedComment);
        when(bookDao.findById(eq(expectedComment.getBook().getId())))
                .thenReturn(testBook);
        commentService.updateComment(expectedComment.getId(),expectedComment.getText(),expectedComment.getBook().getId());
        verify(commentDao,times(1))
                .save(expectedComment);
    }

    @DisplayName("возвращает комментарий по id")
    @Test
    void shouldReadCommentById() {
        final Book testBook = new Book(1,"тест",new Genre(1,"тест"),
                new Author(1,"И","Ф","О"));
        final Comment expectedComment = new Comment(1, "тест комментарий новый",testBook);
        when(commentDao.findById(eq(expectedComment.getId())))
                .thenReturn(expectedComment);
        final Comment actualComment = commentService.readCommentById(expectedComment.getId());
        assertEquals(expectedComment,actualComment);
    }

    @DisplayName("возвращает все комментарии")
    @Test
    void shouldReadComments() {
        final List<Comment> expectedCommentList = List.of(new Comment(1, "тест комментарий1",null),
                new Comment(2, "тест комменатирй2",null));
        when(commentDao.findAll())
                .thenReturn(expectedCommentList);
        final List<Comment> actualCommentList = commentService.readAllComments();
        assertThat(actualCommentList)
                .containsExactlyInAnyOrderElementsOf(expectedCommentList);
    }

    @DisplayName("возвращает все комментарии для книги")
    @Test
    void shouldReadAllCommentsByBookId() {
        final Book testBook = new Book(1,"тест",new Genre(1,"тест"),
                new Author(1,"И","Ф","О"));
        final List<Comment> expectedCommentList = List.of(new Comment(1, "тест комментарий1",testBook),
                new Comment(2, "тест комменатирй2",testBook));
        when(commentDao.findAllCommentsByBookId(eq(testBook.getId())))
                .thenReturn(expectedCommentList);
        final List<Comment> actualCommentList = commentService.readAllCommentsByBookId(testBook.getId());
        assertThat(actualCommentList)
                .containsExactlyInAnyOrderElementsOf(expectedCommentList);
    }

    @DisplayName("удаляет комментарии по id")
    @Test
    void shouldDeleteCommentsById() {
        final Book testBook = new Book(1,"тест",new Genre(1,"тест"),
                new Author(1,"И","Ф","О"));
        final Comment commentForDelete = new Comment(0, "тест комментарий новый",testBook);
        doNothing()
                .when(commentDao)
                .deleteById(eq(commentForDelete.getId()));
        commentService.deleteComment(commentForDelete.getId());
        verify(commentDao,times(1))
                .deleteById(commentForDelete.getId());
    }
}
