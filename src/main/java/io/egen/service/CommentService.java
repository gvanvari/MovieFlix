package io.egen.service;

import java.util.List;

import io.egen.entity.Comment;
import io.egen.entity.Movie;
import io.egen.entity.User;

public interface CommentService {

	Comment findComment(Movie movie, User user);

	Comment addComment(Comment com);

	List<Comment> findCommentByMovie(Movie movie);

}
