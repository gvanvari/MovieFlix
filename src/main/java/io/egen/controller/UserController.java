package io.egen.controller;

import java.util.List;

import io.egen.entity.User;
import io.egen.exception.UserAlreadyExistsException;
import io.egen.exception.UserNotFoundException;

public interface UserController {

	User createUser(User user) throws UserAlreadyExistsException;

	List<User> findAllUser();

	User findUserById(String id) throws UserNotFoundException;

	User findUserByEmail(String email) throws UserNotFoundException;
	
	User findUserByEmailAndPassword(String email, String password)
			throws UserNotFoundException;

	User updateUser(String id, User user) throws UserNotFoundException;

	void deleteUser(String id) throws UserNotFoundException;

}