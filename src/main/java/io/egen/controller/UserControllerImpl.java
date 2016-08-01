package io.egen.controller;

import java.util.List;

import io.egen.entity.User;
import io.egen.exception.UserAlreadyExistsException;
import io.egen.exception.UserNotFoundException;
import io.egen.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
@Api(tags="user")
public class UserControllerImpl implements UserController {
	
	@Autowired
	private UserService userService;
	
	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "create a new user", notes =  "create a new user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public User createUser(@RequestBody User user) throws UserAlreadyExistsException   {
		return userService.createUser(user);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "finds all users", notes = "finds all users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<User> findAllUser() {
		return userService.findAllUsers();
	}
	
	@Override
	@RequestMapping(value = "id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "returns a user with a given ID", notes = "returns a user with a given ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public User findUserById(@PathVariable("id") String id)
			throws  UserNotFoundException {
		return userService.findUserById(id);
	}
	
	@RequestMapping(value = "email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "finds user from email", notes = "finds user from email")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "User is not found in the system"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public User findUserByEmail(@PathVariable("email") String email)
			throws  UserNotFoundException {
		return userService.findUserByEmail(email);
	}
	
	@Override
	@RequestMapping(value = "/login", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "returns a user with the given ID", notes = "returns a user with the given ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public User findUserByEmailAndPassword(@RequestParam("email") String email, @RequestParam("password") String password)
			throws  UserNotFoundException {	
			System.out.println(email);
		 return userService.findUserByEmailAndPassword(email,password);		 
		 
	}
	
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "updates an existing user", notes = "updates an existing user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public User updateUser(@PathVariable("id") String id, @RequestBody User user)
			throws UserNotFoundException {
		return userService.updateUser(id, user);
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "deletes an existing user", notes = "deletes an existing user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void deleteUser(@PathVariable("id") String id)
			throws UserNotFoundException {
		userService.deleteUser(id);
	}
	
	
	
	
	
}
