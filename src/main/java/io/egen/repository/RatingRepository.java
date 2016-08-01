package io.egen.repository;

import java.util.List;

import io.egen.entity.Movie;
import io.egen.entity.Rating;
import io.egen.entity.User;

public interface RatingRepository {

	public Rating addRating(Rating rating);

	public List<Rating> findRatingByMovie(Movie movie);

	public Rating findRating(Movie movie, User user);

}
