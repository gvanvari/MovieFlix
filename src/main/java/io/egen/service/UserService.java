package io.egen.service;

import java.util.List;

import io.egen.entity.User;
import io.egen.exception.UserAlreadyExistsException;
import io.egen.exception.UserNotFoundException;

public interface UserService {

	User createUser(User users) throws UserAlreadyExistsException;

	User findUserById(String id) throws UserNotFoundException;

	List<User> findAllUsers();

	User findUserByEmailAndPassword(String email, String password)
			throws UserNotFoundException;

	User updateUser(String id, User user) throws UserNotFoundException;

	void deleteUser(String id) throws UserNotFoundException;

	User findUserByEmail(String email) throws UserNotFoundException;

}
