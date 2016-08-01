package io.egen.repository;

import io.egen.entity.Movie;

import java.util.List;


public interface MovieRepository {
	
	List<Movie> sortByRating();
	public List<Movie> findAll();
	public Movie findById(String Id);
	public Movie createMovie(Movie movie);
	public Movie updateMovie(Movie movie);
	public void deleteMovie(Movie movie);
	public void insertAllMovie(List<Movie> movies);
	List<Movie> sortByYear();
	List<Movie> sortByVotes();
	Movie findByTitle(String title);
	List<Movie> topRatedMovies(String type);
}
