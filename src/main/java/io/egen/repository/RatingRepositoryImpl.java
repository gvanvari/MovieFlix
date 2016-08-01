package io.egen.repository;

import io.egen.entity.Movie;
import io.egen.entity.Rating;
import io.egen.entity.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class RatingRepositoryImpl implements RatingRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Rating addRating(Rating rating) {
		return em.merge(rating);
	}

	@Override
	public List<Rating> findRatingByMovie(Movie movie) {
		TypedQuery<Rating> queueString = em.createNamedQuery("Rating.findRatingByMovie", Rating.class).setParameter("movie",movie);
		List<Rating> rating = queueString.getResultList();
		if (rating != null) {
			return rating;
		} else {
			return null;
		}

	}

	@Override
	public Rating findRating(Movie movie, User user) {
		TypedQuery<Rating> queueString = em.createQuery("Rating.findRating",Rating.class).setParameter("movie", movie).setParameter("user", user);
		List<Rating> rating = queueString.getResultList();
		if (rating != null && rating.size() == 1) {
			return rating.get(0);
		} else {
			return null;
		}
	}

}
