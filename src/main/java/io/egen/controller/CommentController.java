package io.egen.controller;

import java.util.List;

import io.egen.entity.Comment;
import io.egen.exception.MovieNotFoundException;
import io.egen.exception.UserNotFoundException;

public interface CommentController {

	Comment addComment(String movieId, String userId, String comment)
			throws UserNotFoundException, MovieNotFoundException;

	
	List<String> findCommentByMovie(String movieId) throws MovieNotFoundException;

}