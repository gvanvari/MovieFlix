package io.egen.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@NamedQueries({
		@NamedQuery(name = "Comment.findCommentByMovie", query = "SELECT c FROM Comment c WHERE c.movie = :movie"),
		@NamedQuery(name = "Comment.findCommentByMovieAndUser", query = "SELECT c FROM Comment c WHERE c.movie = :movie and c.user= :user") })

public class Comment {
	@Id
	@GenericGenerator(strategy = "uuid2",name = "myuuid")
	@GeneratedValue(generator = "myuuid")
	private String id;

	private String comment;

	@ManyToOne
	private Movie movie;

	@ManyToOne
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
