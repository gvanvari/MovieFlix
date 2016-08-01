package io.egen.repository;

import io.egen.entity.User;

import java.util.List;

//import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User findUserById(String id) {
		User user = em.find(User.class, id);
		return user;
	}
	
	
	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		TypedQuery<User> queueString = em.createNamedQuery("User.findUserByEmailAndPassword", User.class);
		 queueString.setParameter("email", email);
		 queueString.setParameter("password", password);
		List<User> user = queueString.getResultList();
		if (user != null && user.size() == 1) {
			return user.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User createUser(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public List<User> findAllUsers() {

		TypedQuery<User> query = em.createQuery("SELECT u FROM User u ORDER BY u.id ASC", User.class);

		List<User> User = query.getResultList();
		return User;

	}

	

	@Override
	public User updateUser(User user) {

		return em.merge(user);
	}

	@Override
	public void deleteUser(User user) {
		em.remove(user);

	}


	@Override
	public User findUserByEmail(String email) {
		TypedQuery<User> queueString = em.createNamedQuery("User.findUserByEmail", User.class);
		queueString.setParameter("email", email);
		List<User> user = queueString.getResultList();

		if (user != null && user.size() == 1) {
			return user.get(0);

		} else {
			return null;
		}
	}

}
