package io.egen.controller;

import java.util.List;

import io.egen.entity.Movie;
import io.egen.exception.MovieAlreadyExistsException;
import io.egen.exception.MovieNotFoundException;

public interface MovieController {

	public List<Movie> findAll();

	Movie findById(String id) throws MovieNotFoundException;

	Movie findByTitle(String title) throws MovieNotFoundException;

	List<Movie> topRatedMovies(String type) throws MovieNotFoundException;

	List<Movie> sortByRating();

	List<Movie> sortByYear();

	List<Movie> sortByVotes();

	Movie createMovie(Movie movie) throws MovieAlreadyExistsException;

	void insertAllMovie(List<Movie> Movies);

	Movie updateMovie(String id, Movie movie) throws MovieNotFoundException;

	void deleteMovie(String id) throws MovieNotFoundException;

}