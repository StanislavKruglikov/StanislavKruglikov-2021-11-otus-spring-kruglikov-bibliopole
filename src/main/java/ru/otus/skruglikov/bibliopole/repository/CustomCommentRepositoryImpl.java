package ru.otus.skruglikov.bibliopole.repository;

import lombok.RequiredArgsConstructor;
import ru.otus.skruglikov.bibliopole.domain.Comment;

import java.util.List;

@RequiredArgsConstructor
public class CustomCommentRepositoryImpl implements CustomCommentRepository {

    private final BookRepository bookRepository;

    public List<Comment> findByBookId(long bookId) {
        /*final TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :bookId",Comment.class);
        final List<Comment> commentList = query.setParameter("bookId",bookId).getResultList();
        if(commentList.size() > 0) {
            bookRepository.findById(bookId);
        }
        return commentList;*/
        return null;
    }
}
