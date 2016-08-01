package io.egen.controller;

import io.egen.entity.Rating;
import io.egen.exception.MovieNotFoundException;
import io.egen.exception.UserNotFoundException;

public interface RatingController {

	Rating addRating(String movieId, String userId, Long avgRating)
			throws UserNotFoundException, MovieNotFoundException;

	Long findRatingByMovie(String movieId) throws MovieNotFoundException;

}