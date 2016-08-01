package io.egen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
@NamedQueries ({
	@NamedQuery(name="Movie.filterByType", query="SELECT m FROM Movie m WHERE m.type = :type"),
	@NamedQuery(name="Movie.filterByYear", query="SELECT m FROM Movie m WHERE m.year = :year"),
	@NamedQuery(name="Movie.filterByGenre", query="SELECT m FROM Movie m WHERE m.genre like :genre"),
	@NamedQuery(name="Movie.findByTitle", query="SELECT m FROM Movie m WHERE m.title = :title"),
	@NamedQuery(name="Movie.topRatedMovies", query="SELECT m FROM Movie m WHERE m.type = :type order by m.imdbRating desc")
})
public class Movie {
	@Id
	@GenericGenerator(strategy = "uuid2",name = "myuuid")
	@GeneratedValue(generator = "myuuid")
	private String id;

	private String released;

	private String type;

	private String imdbVotes;

	private String runtime;

	private String poster;

	private String imdbID;

	private String country;

	private String title;

	private String imdbRating;

	private String year;

	private String rated;

	private String actors;
	@Column(columnDefinition="TEXT")
	private String plot;

	private String metascore;
	
	@Column(columnDefinition="TEXT")
	private String writer;

	private String genre;

	private String language;

	private String awards;

	private String director;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JsonProperty(value="Released")
	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	@JsonProperty(value="Type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		String result = imdbVotes.replaceAll("[-+.^:,]","");
		
		this.imdbVotes = result;
	}
	@JsonProperty(value="Runtime")
	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	@JsonProperty(value="Poster")
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	@JsonProperty(value="Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty(value="Title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	@JsonProperty(value="Year")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	@JsonProperty(value="Rated")
	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}
	@JsonProperty(value="Actors")
	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}
	@JsonProperty(value="Plot")
	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}
	@JsonProperty(value="Metascore")
	public String getMetascore() {
		return metascore;
	}

	public void setMetascore(String metascore) {
		this.metascore = metascore;
	}
	@JsonProperty(value="Writer")
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	@JsonProperty(value="Genre")
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	@JsonProperty(value="Language")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	@JsonProperty(value="Awards")
	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}
	@JsonProperty(value="Director")
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}


}
