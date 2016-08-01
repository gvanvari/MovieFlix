package io.egen.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;



import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@NamedQueries(
		{
			@NamedQuery(name = "Rating.findRatingByMovie", query = "SELECT r FROM Rating r WHERE r.movie = :movie"),
			@NamedQuery(name= "Rating.findRating", query= "SELECT r FROM Rating r WHERE r.movie = :movie and r.user= :user")
		})

public class Rating {
	@Id
	@GenericGenerator(strategy = "uuid2",name = "myuuid")
	@GeneratedValue(generator = "myuuid")
	private String id;

	private Long rating;

	@OneToOne
	private Movie movie;
	
	@ManyToOne
	private User user;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getRating() {
		return rating;
	}
	public void setRating(Long rating) {
		this.rating = rating;
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
