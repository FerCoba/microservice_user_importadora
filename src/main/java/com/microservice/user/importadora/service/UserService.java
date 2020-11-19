package com.microservice.user.importadora.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.user.importadora.entities.User;
import com.microservice.user.importadora.exception.UserException;
import com.microservice.user.importadora.repositories.UserRepository;
import com.microservice.user.importadora.util.EnumDepartment;
import com.microservice.user.importadora.util.UserBuilder;

@Service
public class UserService {

	private static final Logger loggerUserService = Logger.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	public int insertNewUser(UserBuilder user) {
		int result = 1;
		try {

			List<Integer> departmentsValid = new ArrayList<>();

			result = validateDepartments(user, result, departmentsValid);

			userRepository.insertNewUser(user.getUser(), user.getPassword(), user.getDepartId(), user.getPrivId(),
					user.getUserName(), user.getRole());
		} catch (Exception e) {
			loggerUserService.error("the following error occurred : ".concat(e.getMessage()));
			throw new UserException("001", "parameter/s not valid.");
		}
		return result;
	}

	public List<User> obtainInformationOfAllUser() {
		List<User> usersList = new ArrayList<>();
		try {
			usersList = userRepository.obtainInformationOfAllUserList();
			generateDepartmentId(usersList);

			generatePrivilegeId(usersList);
			System.out.println( " usersList " + usersList.toString());
		} catch (Exception exceptionObtainInformationOfAllUser) {
			loggerUserService
					.error("the following error occurred : ".concat(exceptionObtainInformationOfAllUser.getMessage()));
		}
		return usersList;
	}

	public User obtainUserAndPasswordByUserName(String userName) {
		User user = new User();
		try {
			user = userRepository.obtainUserAndPassword(userName);
		} catch (Exception exceptionObtainUserAndPasswordByUseName) {
			loggerUserService.error(
					"the following error occurred : ".concat(exceptionObtainUserAndPasswordByUseName.getMessage()));
			throw exceptionObtainUserAndPasswordByUseName;
		}
		return user;
	}

	public int deleteUserByUser(String user) {

		int result = 1;

		if (!userRepository.userNameList().contains(user)) {
			throw new UserException("003", " the user entered does not exist. ");
		}
		try {
			userRepository.deleteUserByUser(user);
		} catch (Exception exceptionDeleteUser) {
			loggerUserService.error("the following error occurred : ".concat(exceptionDeleteUser.getMessage()));
			throw new UserException("003", "fail deleting user.");
		}

		return result;
	}

	public int updateUseByUser(UserBuilder user) {

		int resultado = 1;

		if (!userRepository.userNameList().contains(user.getUser())) {
			throw new UserException("004", " the user entered does not exist. ");
		}

		try {
			resultado = userRepository.updateUserInformation(user.getUser(), user.getPassword(), user.getDepartId(),
					user.getPrivId(), user.getUserName(), user.getRole());
		} catch (Exception exceptionUpdateUser) {
			loggerUserService.error("the following error occurred : ".concat(exceptionUpdateUser.getMessage()));
			throw new UserException("004", "fail updating user.");
		}
		return resultado;
	}

	private void generatePrivilegeId(List<User> usersList) {
		for (int counterPrivilegeId = 0; counterPrivilegeId < usersList.size(); counterPrivilegeId++) {
			int privilegeId = usersList.get(counterPrivilegeId).getPrivileges().getPrivilegeId();
			usersList.get(counterPrivilegeId).setPrivId(privilegeId);
		}
	}

	private void generateDepartmentId(List<User> usersList) {
		for (int counterDepartmentId = 0; counterDepartmentId < usersList.size(); counterDepartmentId++) {
			int departmentId = usersList.get(counterDepartmentId).getDepartment().getDepartmentId();
			usersList.get(counterDepartmentId).setDepartId(departmentId);
		}
	}

	private int validateDepartments(UserBuilder user, int result, List<Integer> departmentsValid) {
		for (EnumDepartment enumElementsDepartment : EnumDepartment.values()) {
			departmentsValid.add(enumElementsDepartment.getCode());
		}

		if (!departmentsValid.contains(user.getDepartId())) {
			result = -1;
		}
		return result;
	}

}
