package io.egen.controller;

import java.util.ArrayList;
import java.util.List;

import io.egen.entity.Comment;
import io.egen.entity.Movie;
import io.egen.entity.User;
import io.egen.exception.MovieNotFoundException;
import io.egen.exception.UserNotFoundException;
import io.egen.service.CommentService;
import io.egen.service.MovieService;
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
@RequestMapping(value="/comment")
@Api(tags="comment")
public class CommentControllerImpl implements CommentController {

	@Autowired
	UserService userService;

	@Autowired
	CommentService commentService;

	@Autowired
	MovieService movieService;

	@Override
	@RequestMapping(value = "{movieid}/{userid}/{comment}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "returns a comment for a movie", notes = "returns a comment for a movie")
	public Comment addComment(@PathVariable("movieid") String movieId,
			@PathVariable("userid") String userId,
			@PathVariable("comment") String comment) throws UserNotFoundException, MovieNotFoundException {

		Movie movie = movieService.findById(movieId);
		User user = userService.findUserById(userId);
		Comment comments = commentService.findComment(movie, user);
		if (comments == null) {
			comments = new Comment();
			comments.setMovie(movie);
			comments.setUser(user);

		}
		comments.setComment(comment);
		return commentService.addComment(comments);
	}

	//@SuppressWarnings("null")
	@Override
	@RequestMapping(value = "{movieid}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "returns a  comment for the movie", notes = "returns a  comment for the movie")
	public List<String> findCommentByMovie(@PathVariable("movieid") String movieId) throws MovieNotFoundException {

		Movie movie = movieService.findById(movieId);
		List<Comment> comments = commentService.findCommentByMovie(movie);
		List<String> com=new ArrayList<String>();
		for(Comment comment : comments){
		   com.add(comment.getComment());
		}
		return com;
	}

}
