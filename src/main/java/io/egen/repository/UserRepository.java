package io.egen.repository;

import java.util.List;

import io.egen.entity.User;

public interface UserRepository {

	public User findUserById(String id);

	public User createUser(User user);

	public List<User> findAllUsers();

	public User findUserByEmailAndPassword(String email, String password);
	
	public User updateUser(User user);

	public void deleteUser(User user);

	public User findUserByEmail(String email);



}
