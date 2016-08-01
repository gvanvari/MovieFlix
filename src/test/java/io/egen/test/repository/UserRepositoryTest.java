package io.egen.test.repository;

import io.egen.entity.User;
import io.egen.repository.UserRepository;
import io.egen.repository.UserRepositoryImpl;
import io.egen.test.TestConfig;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class UserRepositoryTest {

	@Mock
	private EntityManager em;

	@InjectMocks
	private UserRepository userRepository = new UserRepositoryImpl();

	@Mock
	private TypedQuery<User> query;
	private User user;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setPassword("dummy");
		user.setEmail("dummy@dummy.com");
		user.setFirstName("Dummy");
		user.setLastName("User");
		user.setId(UUID.randomUUID().toString());

	}

	@Test
	public void testFindAllUsers() {
		List<User> expected = Arrays.asList(user);

		Mockito.when(em.createQuery("SELECT u FROM User u ORDER BY u.id ASC", User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);

		List<User> users = userRepository.findAllUsers();
		Assert.assertEquals(expected, users);

	}

	@Test
	public void testFindUserById() {
		Mockito.when(em.find(User.class, user.getId())).thenReturn(user);
		User actual = userRepository.findUserById(user.getId());

		Assert.assertEquals(user, actual);
	}
	
	
	@Test
	public void testFindUserByEmailIdAndPassword()
	{
		List<User> expected = Arrays.asList(user);
		Mockito.when(em.createNamedQuery("User.findUserByEmailAndPassword", User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		
		User actual = userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
		Assert.assertEquals(user, actual);
	}
	
	@Test
	public void testFindUserByEmailIdAndPasswordNull()
	{
		
		Mockito.when(em.createNamedQuery("User.findUserByEmailAndPassword", User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		
		User actual = userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
		Assert.assertEquals(null, actual);
	}
	
	@Test
	public void testCreateUser(){
		userRepository.createUser(user);
		Mockito.verify(em).persist(user);
	}

	@Test
	public void testUpdateUser(){
		userRepository.updateUser(user);
		Mockito.verify(em).merge(user);
	}
	@Test
	public void testDeleteUser(){
		userRepository.deleteUser(user);
		Mockito.verify(em).remove(user);
	}
}
