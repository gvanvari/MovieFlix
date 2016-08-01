package io.egen.repository;

import io.egen.entity.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class MovieRepositoryImpl implements MovieRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Movie> findAll() {
		
		TypedQuery<Movie> queryString  = em.createQuery("SELECT m FROM Movie m ORDER BY m.id ASC", Movie.class);
		List<Movie> movies= queryString.getResultList();
		return movies;
	}

	@Override
	public Movie findById(String Id) {
		Movie movie= em.find(Movie.class, Id);
		return movie;
	}
	
	@Override
	public Movie findByTitle(String title) {
		TypedQuery<Movie> queryString = em.createNamedQuery("Movie.findByTitle", Movie.class);
		queryString.setParameter("title",title);
         List<Movie> movie = queryString.getResultList();
          if (movie != null && movie.size() == 1 ) {
	         return movie.get(0);
             } 
          else {
        	  return null;
	      }
      }

	@Override
	public Movie createMovie(Movie movie) {
		 em.persist(movie);
		return movie;
	}

	@Override
	public Movie updateMovie(Movie movie) {
		return em.merge(movie);
	}

	@Override
	public void deleteMovie(Movie movie) {
		em.remove(movie);
	}

	@Override
	public void insertAllMovie(List<Movie> movies) {
		for(Movie m : movies){
			em.persist(m);
		}
	}
	
	@Override
	public List<Movie> topRatedMovies(String type) {
		
		TypedQuery<Movie> queryString = em.createNamedQuery("Movie.topRatedMovies", Movie.class).setParameter("type",type);
		List<Movie> movies= new ArrayList<Movie>();
		List<Movie> movie = queryString.getResultList();
		
		if (movie.size() >0) {
			for(int i=0;i<10;i++){
				movies.add(movie.get(i));	
			}
			return movies;
		} 
		else {
			return null;
		}
	}

	@Override
	public List<Movie> sortByRating() {
		
		TypedQuery<Movie> queryString = em.createQuery("SELECT m FROM Movie m ORDER BY m.imdbRating DESC ", Movie.class);
		List<Movie> movies = queryString.getResultList();
		return movies;
	}

	@Override
	public List<Movie> sortByYear() {
		TypedQuery<Movie> queryString = em.createQuery("SELECT m FROM Movie m ORDER BY m.year DESC ", Movie.class) ;
		List<Movie> movies = queryString.getResultList();
		return movies;
	}

	@Override
	public List<Movie> sortByVotes() {
		TypedQuery<Movie> queryString = em.createQuery("SELECT m FROM Movie m ORDER BY m.imdbVotes DESC ", Movie.class);
		List<Movie> movies = queryString.getResultList();
		return movies;
	}

	

	

	

	
}
