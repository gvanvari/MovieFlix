package io.egen.repository;

import io.egen.entity.Comment;
import io.egen.entity.Movie;
import io.egen.entity.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CommentRepositoryImpl implements CommentRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Comment findComment(Movie movie, User user) {
		TypedQuery<Comment> queueString = em.createNamedQuery("Comment.findCommentByMovieAndUser",Comment.class).setParameter("movie", movie).setParameter("user", user);
		List<Comment> comment = queueString.getResultList();
		if (comment != null && comment.size() == 1) {
			return comment.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Comment addComment(Comment comment) {
		return em.merge(comment);
	}

	@Override
	public List<Comment> findCommentByMovie(Movie movie) {
		TypedQuery<Comment> queueString = em.createNamedQuery("Comment.findCommentByMovie", Comment.class).setParameter("movie", movie);
		List<Comment> comment = queueString.getResultList();
		if (comment != null) {
			return comment;
		} else {
			return null;
		}

	}

}
