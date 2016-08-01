package io.egen.service;

import io.egen.entity.Movie;
import io.egen.exception.MovieAlreadyExistsException;
import io.egen.exception.MovieNotFoundException;

import java.util.List;

public interface MovieService {

	List<Movie> findAll();

	Movie findById(String Id) throws MovieNotFoundException;

	Movie createMovie(Movie movie) throws MovieAlreadyExistsException;

	Movie updateMovie(String Id, Movie movie) throws MovieNotFoundException;

	void deleteMovie(String Id) throws MovieNotFoundException;

	void insertAllMovie(List<Movie> movies);

	List<Movie> sortByRating();

	List<Movie> sortByYear();

	List<Movie> sortByVotes();

	Movie findByTitle(String title) throws MovieNotFoundException;

	List<Movie> topRatedMovies(String type);

}
