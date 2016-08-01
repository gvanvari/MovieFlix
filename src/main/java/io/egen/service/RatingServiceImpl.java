package io.egen.service;

import io.egen.entity.Movie;
import io.egen.entity.Rating;
import io.egen.entity.User;
import io.egen.repository.RatingRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating addRating(Rating rating) {
		Rating rate = ratingRepository.addRating(rating);
		return rate;
	}

	@Override
	public List<Rating> findRatingByMovie(Movie movie) {

		return ratingRepository.findRatingByMovie(movie);
	}

	@Override
	public Rating findRating(Movie movie, User user) {
		
		return ratingRepository.findRating(movie,user);
	}

}
