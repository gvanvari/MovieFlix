package io.egen.service;

import io.egen.entity.Comment;
import io.egen.entity.Movie;
import io.egen.entity.User;
import io.egen.repository.CommentRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository commentRepository;
	
	
	
	@Override
	public Comment findComment(Movie movie, User user) {
	
		return commentRepository.findComment(movie,user);
	}

	@Override
	public Comment addComment(Comment com) {
		Comment comment= commentRepository.addComment(com);
		return comment;
	}

	@Override
	public List<Comment> findCommentByMovie(Movie movie) {
		
		return commentRepository.findCommentByMovie(movie);
	}

}
