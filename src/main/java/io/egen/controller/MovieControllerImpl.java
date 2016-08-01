package io.egen.controller;

import java.util.List;

import io.egen.entity.Movie;
import io.egen.exception.MovieAlreadyExistsException;
import io.egen.exception.MovieNotFoundException;
import io.egen.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies")
@Api(tags = "movies")
public class MovieControllerImpl implements MovieController {

	@Autowired
	private MovieService service;

	@Override
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "finds all movies", notes = "finds all movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Movie> findAll() {
		return service.findAll();
	}

	@Override
	@RequestMapping(value = "id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "finds movie with a given ID", notes = "finds movie with a given ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Movie Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Movie findById(@PathVariable("id") String id)
			throws MovieNotFoundException {
		return service.findById(id);
	}

	@Override
	@RequestMapping(value = "title/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "finds movie with a given title", notes =  "finds movie with a given title")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Movie Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Movie findByTitle(@PathVariable("title") String title)
			throws MovieNotFoundException {
		return service.findByTitle(title);
	}

	@Override
	@RequestMapping(value = "topRated/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "fetches top 10 movies", notes = "fetches top 10 movies")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Movie Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Movie> topRatedMovies(@PathVariable("type") String type)
			throws MovieNotFoundException {
		return service.topRatedMovies(type);
	}
	
	@Override
	@RequestMapping(value = "sort/rating",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "sorts all movies by rating", notes = "sorts all movies by rating")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Movie> sortByRating() {
		return service.sortByRating();
	}
	
	@Override
	@RequestMapping(value = "sort/year",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "sorts all movies by year", notes = "sorts all movies by year")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Movie> sortByYear() {
		return service.sortByYear();
	}
	
	@Override
	@RequestMapping(value = "sort/votes",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "sorts all movies by votes", notes = "sorts all movies by votes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Movie> sortByVotes() {
		return service.sortByVotes();
	}
	
	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "creates a new movie", notes = "creates a new movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Movie createMovie(@RequestBody Movie movie)
			throws MovieAlreadyExistsException {
		return service.createMovie(movie);
	}
	
	@Override
	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "inserts all movies", notes = "inserts all movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void insertAllMovie(@RequestBody List<Movie> Movies){
		service.insertAllMovie(Movies);
	}
	
	@Override
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "updates an existing movie", notes = "updates an existing movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Movie Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Movie updateMovie(@PathVariable("id") String id, @RequestBody Movie movie)
			throws MovieNotFoundException {
		return service.updateMovie(id, movie);
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "deletes an existing movie", notes = "deletes an existing movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Movie Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void deleteMovie(@PathVariable("id") String id)
			throws MovieNotFoundException {
		service.deleteMovie(id);
	}

}
