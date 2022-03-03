package ru.otus.skruglikov.bibliopole.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.skruglikov.bibliopole.domain.Comment;
import ru.otus.skruglikov.bibliopole.exception.CommentNotFoundDaoException;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


@DisplayName("класс CommentDaoJpaImpl должен")
@DataJpaTest
@Import({CommentDaoJpaImpl.class,BookDaoJpaImpl.class})
class CommentDaoJpaTest {

  @Autowired
  private TestEntityManager em;

  @Autowired
  private CommentDaoJpaImpl commentDao;

  @Autowired
  private BookDaoJpaImpl bookDao;

  @DisplayName("добавлять комменатрий")
  @Test
  void shouldAddComment() {
    final Comment expectedComment = commentDao.save(new Comment(0,"test4",bookDao.findById(1)));
    final Comment actualComment = commentDao.findById(4);
    assertThat(actualComment)
            .usingRecursiveComparison()
            .isEqualTo(expectedComment);
  }

  @DisplayName("обновлять комменатрий")
  @Test
  void shouldUpdateComment() {
    final Comment commentToUpdate = commentDao.findById(1);
    commentToUpdate.setText("edited text");
    Comment editedComment = commentDao.save(commentToUpdate);
    assertThat(commentDao.findById(editedComment.getId()))
            .usingRecursiveComparison()
            .isEqualTo(commentToUpdate);
  }

  @DisplayName("находить все комменатрий для книги")
  @Test
  void shouldFindAllCommentsByBookId() {
    final List<Comment> actualAllBookIdComments = commentDao.findAllCommentsByBookId(1);
    assertThat(actualAllBookIdComments)
            .containsExactlyInAnyOrder(commentDao.findById(1),commentDao.findById(2));
  }

  @DisplayName("находить комменатрий по id")
  @Test
  void shouldFindCommentById() {
    final Comment expectedComment = new Comment(1,"тест комментарий1",bookDao.findById(1));
    final Comment actualComment = commentDao.findById(1);
    assertThat(actualComment)
            .usingRecursiveComparison()
            .isEqualTo(expectedComment);
  }


  @DisplayName("удаляет комменатрий по id")
  @Test
  void shouldDeleteCommentById() {
    final Comment commentBeforeDelete = commentDao.findById(1);
    commentDao.deleteById(1);
    em.detach(commentBeforeDelete);
    assertThrowsExactly(CommentNotFoundDaoException.class,() -> commentDao.findById(1));
  }
}