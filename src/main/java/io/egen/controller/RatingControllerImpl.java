package io.egen.controller;

import java.util.List;

import io.egen.entity.Movie;
import io.egen.entity.Rating;
import io.egen.entity.User;
import io.egen.exception.MovieNotFoundException;
import io.egen.exception.UserNotFoundException;
import io.egen.service.MovieService;
import io.egen.service.RatingService;
import io.egen.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rating")
@Api(tags = "rating")
public class RatingControllerImpl implements RatingController {


	@Autowired
	UserService userService;

	@Autowired
	RatingService ratingService;

	@Autowired
	MovieService movieService;

	@Override
	@RequestMapping(value = "{movieid}/{userid}/{ratingvalue}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "returns rating for a movie", notes = "returns rating for a movie")
	public Rating addRating(@PathVariable("movieid") String movieId,
			@PathVariable("userid") String userId,
			@PathVariable("ratingvalue") Long ratingValue) throws UserNotFoundException, MovieNotFoundException {

		Movie movie = movieService.findById(movieId);
		User user = userService.findUserById(userId);
		Rating rating = ratingService.findRating(movie, user);
		if (rating == null) {
			rating = new Rating();
			rating.setMovie(movie);
			rating.setUser(user);

		}
		rating.setRating(ratingValue);
		return ratingService.addRating(rating);
	}

	@Override
	@RequestMapping(value = "{movieid}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "returns rating for a movie", notes = "returns rating for a movie")
	public Long findRatingByMovie(@PathVariable("movieid") String movieId) throws MovieNotFoundException {

		Movie movie = movieService.findById(movieId);
		List<Rating> ratings = ratingService.findRatingByMovie(movie);
		long avgRating = 0;
		if (ratings != null) {
			for (Rating rating : ratings) {
				avgRating += rating.getRating();
			}
			avgRating = (avgRating / ratings.size());
		}

		return avgRating;
	}

}
