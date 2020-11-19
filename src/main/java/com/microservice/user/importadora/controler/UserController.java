package com.microservice.user.importadora.controler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.user.importadora.entities.User;
import com.microservice.user.importadora.exception.UserException;
import com.microservice.user.importadora.response.Response;
import com.microservice.user.importadora.service.UserService;
import com.microservice.user.importadora.util.UserBuilder;

@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {

	private static final Logger loggerUserController = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@GetMapping(value = "/obtainInformationOfAllUsers")
	public ResponseEntity<Response> obtainInformationOfAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			users = userService.obtainInformationOfAllUser();
		} catch (Exception exceptionObtainInformationOfAllUsers) {
			loggerUserController
					.error("the following error occurred : ".concat(exceptionObtainInformationOfAllUsers.getMessage()));
			throw exceptionObtainInformationOfAllUsers;
		}
		return new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK), null, users), HttpStatus.OK);
	}

	@GetMapping(value = "/{userName}/UserAndPassword")
	public ResponseEntity<Response> obtainUserAndPassword(@PathVariable String userName) {
		User user = new User();
		try {
			user = userService.obtainUserAndPasswordByUserName(userName);
		} catch (Exception exceptionObtainUserAndPassword) {
			loggerUserController
					.error("the following error occurred : ".concat(exceptionObtainUserAndPassword.getMessage()));
			throw exceptionObtainUserAndPassword;
		}
		return new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK), null, user), HttpStatus.OK);
	}

	@PostMapping(path = "/insertNewUser")
	public ResponseEntity<Response> insertUser(@RequestBody UserBuilder user) throws Exception {
		UserBuilder builder = new UserBuilder.Builder().addUser(user.getUser()).addPassword(user.getPassword())
				.addDepartId(user.getDepartId()).addPrivId(user.getPrivId()).addUserName(user.getUserName())
				.addRole(user.getRole()).buildInsertNewUser();
		int result = 0;
		try {
			result = userService.insertNewUser(builder);

		} catch (UserException insertUserException) {
			loggerUserController.error("the following error occurred : " + insertUserException.getMessage());
			throw new UserException("Parameters not valid.");
		} catch (Exception e) {
			loggerUserController.error("the following error occurred : " + e.getMessage());
			throw new Exception();
		}

		return result == -1
				? new ResponseEntity<>(new Response(String.valueOf(HttpStatus.NOT_FOUND),
						"el valor ingresado no existe, los valores posibles son", null), HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK), "User successfully inserted", null),
						HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteUser/{user}")
	public ResponseEntity<Response> deleteUser(@PathVariable("user") String user) {

		try {
			userService.deleteUserByUser(user);

		} catch (UserException deleteUserException) {
			loggerUserController.error("the following error occurred : " + deleteUserException.getMessage());
			throw new UserException(deleteUserException.getMessage());
		}

		return new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK), "User successfully deleted.", null),
				HttpStatus.OK);
	}
	
	@PutMapping(path = "/updateUser")
	public ResponseEntity<Response> updateUser(@RequestBody UserBuilder user) {
		UserBuilder builder = new UserBuilder.Builder().addUser(user.getUser()).addPassword(user.getPassword())
				.addDepartId(user.getDepartId()).addPrivId(user.getPrivId()).addUserName(user.getUserName())
				.addRole(user.getRole()).buildInsertNewUser();
		
		try {
			userService.updateUseByUser(builder);

		} catch (UserException deleteUserException) {
			loggerUserController.error("the following error occurred : " + deleteUserException.getMessage());
			throw new UserException(deleteUserException.getMessage());
		}

		return new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK), "User update successfully.", null),
				HttpStatus.OK);
	}

}
