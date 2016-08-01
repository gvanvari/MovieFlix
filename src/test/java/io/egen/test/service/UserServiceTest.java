package io.egen.test.service;

import java.util.UUID;

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

import io.egen.entity.User;
import io.egen.exception.UserAlreadyExistsException;
import io.egen.exception.UserNotFoundException;
import io.egen.repository.UserRepository;
import io.egen.service.UserService;
import io.egen.service.UserServiceImpl;
import io.egen.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService = new UserServiceImpl();

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
	public void testCreateUser() throws UserAlreadyExistsException {
		Mockito.when(userRepository.findUserById(user.getId())).thenReturn(null);
		userService.createUser(user);
		Mockito.verify(userRepository).createUser(user);

	}
	
	@Test(expected = UserAlreadyExistsException.class)
	public void testCreateUserException() throws UserAlreadyExistsException {
		Mockito.when(userRepository.findUserById(user.getId())).thenReturn(user);
		userService.createUser(user);

	}
	
	@Test
	public void testFindAllUsers() {
		userService.findAllUsers();
		Mockito.verify(userRepository).findAllUsers();
	}

	@Test
	public void testFindUserById() throws UserNotFoundException {
		Mockito.when(userRepository.findUserById(user.getId())).thenReturn(user);
		User actual = userService.findUserById(user.getId());

		Assert.assertEquals(user, actual);

	}

	@Test(expected = UserNotFoundException.class)
	public void testFindUserByIdException() throws UserNotFoundException {
		Mockito.when(userRepository.findUserById(user.getId())).thenReturn(null);
		userService.findUserById(user.getId());
	}

	@Test
	public void testFindUserByEmailAndPassword() throws UserNotFoundException {
		Mockito.when(userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);
		User actual = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());

		Assert.assertEquals(user, actual);

	}

	@Test(expected = UserNotFoundException.class)
	public void testFindUserByEmailAndPasswordException() throws UserNotFoundException {
		Mockito.when(userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(null);
		userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());

	}

	@Test
	public void testUpdateUser() throws UserNotFoundException {
		Mockito.when(userRepository.findUserById(user.getId())).thenReturn(user);
		userService.updateUser(user.getId(), user);
		Mockito.verify(userRepository).updateUser(user);

	}
	@Test(expected = UserNotFoundException.class)
	public void testUpdateUserException() throws UserNotFoundException {
		Mockito.when(userRepository.findUserById(user.getId())).thenReturn(null);
		userService.updateUser(user.getId(), user);
	}

	@Test(expected = UserNotFoundException.class)
	public void testDeleteUserException() throws UserNotFoundException {
		Mockito.when(userRepository.findUserById(user.getId())).thenReturn(null);
		userService.deleteUser(user.getId());
	}

	@Test
	public void testDeleteUser() throws UserNotFoundException {
		Mockito.when(userRepository.findUserById(user.getId())).thenReturn(user);
		userService.deleteUser(user.getId());
		Mockito.verify(userRepository).deleteUser(user);
	}
}
