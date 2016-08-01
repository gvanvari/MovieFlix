package io.egen.service;

import io.egen.entity.User;
import io.egen.exception.UserAlreadyExistsException;
import io.egen.exception.UserNotFoundException;
import io.egen.repository.UserRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public User findUserById(String id) throws UserNotFoundException {
		User existing = userRepository.findUserById(id);
		if(existing == null){
			throw new UserNotFoundException();
		}
		else{
			return existing;
		}
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAllUsers();
	}

	@Override
	public User findUserByEmailAndPassword(String email, String password)
			throws UserNotFoundException {
		User existing = userRepository.findUserByEmailAndPassword(email,password);
		if(existing == null ){
			throw new UserNotFoundException();
		}else{
			return existing;
		}
	}

	@Override
	public User updateUser(String id, User user) throws UserNotFoundException {
		User existing = userRepository.findUserById(id);
		if(existing == null){
			throw new UserNotFoundException();
		}
		else{
			return userRepository.updateUser(user);
		}
	}

	@Override
	public void deleteUser(String id) throws UserNotFoundException {
		User existing = userRepository.findUserById(id);
		if(existing == null){
			throw new UserNotFoundException();
		}
		else{
			 userRepository.deleteUser(existing);
		}

	}
	
	@Override
	public User createUser(User user) throws UserAlreadyExistsException {
		User existing= userRepository.findUserByEmail(user.getEmail());
		if(existing == null){
			return userRepository.createUser(user);
		}
		else
		{
			throw new UserAlreadyExistsException(); 
		}
		 
	}

	@Override
	public User findUserByEmail(String email) throws UserNotFoundException {
		User existing = userRepository.findUserByEmail(email);
		if(existing == null){
			throw new UserNotFoundException();
		}
		else{
			return existing;
		}
	}


}
