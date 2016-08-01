package io.egen.service;

import io.egen.entity.Movie;
import io.egen.exception.MovieAlreadyExistsException;
import io.egen.exception.MovieNotFoundException;
import io.egen.repository.MovieRepository;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	@Override
	public Movie findById(String Id) throws MovieNotFoundException {
		Movie movie=movieRepository.findById(Id);
		if(movie==null){
			throw new MovieNotFoundException() ;
		}else{
		return movie;
		}
	}
	

	@Override
	public Movie findByTitle(String title) throws MovieNotFoundException {
		Movie movie=movieRepository.findByTitle(title);
		if(movie==null){
			throw new MovieNotFoundException() ;
		}else{
		return movie;
		}
	}
	
	@Override
	public List<Movie> topRatedMovies(String type) {
		List<Movie> movie= movieRepository.topRatedMovies(type);
		if(movie.size()==0){
			return null;
		}
		else{
		return movie;
		}
	}

	@Override
	public Movie createMovie(Movie movie) throws MovieAlreadyExistsException {
		Movie existing =  movieRepository.findById(movie.getImdbID());
		if(existing == null) {
			return movieRepository.createMovie(movie);
		}
		else {
			throw new MovieAlreadyExistsException();
		}
		
	}

	@Override
	public Movie updateMovie(String Id, Movie movie) throws MovieNotFoundException {
		Movie existing =  movieRepository.findById(Id);
		if(existing == null) {
			throw new MovieNotFoundException();
		}
		else {
			return movieRepository.updateMovie(movie);
		}
	}

	@Override
	public void deleteMovie(String Id) throws MovieNotFoundException {
		Movie existing =  movieRepository.findById(Id);
		if(existing == null) {
			throw new MovieNotFoundException();
		}
		else {
			movieRepository.deleteMovie(existing);
		}
	}

	@Override
	public void insertAllMovie(List<Movie> movies) {
		movieRepository.insertAllMovie(movies);
		
	}

	@Override
	public List<Movie> sortByRating() {
		
		return movieRepository.sortByRating();
	}

	@Override
	public List<Movie> sortByYear() {
		return movieRepository.sortByYear();
	}

	@Override
	public List<Movie> sortByVotes() {
		return movieRepository.sortByVotes();
	}

	

	
	



	
}
