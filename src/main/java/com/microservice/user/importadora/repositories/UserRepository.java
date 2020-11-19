package com.microservice.user.importadora.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.user.importadora.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO User(user,password,department_id,privilege_id,user_name,role) "
			+ "VALUES(:user,:password,:departmentId,:provilegeId,:userName,:role)", nativeQuery = true)
	void insertNewUser(@Param("user") String user, @Param("password") String password,
			@Param("departmentId") int departmentId, @Param("provilegeId") int provilegeId,
			@Param("userName") String userName, @Param("role") String role);

	@Query(value = " SELECT new com.microservice.user.importadora.entities.User(users.userId, users.department, "
			+ " users.privileges, users.user, users.password, users.userName, users.role)" + " FROM User users")
	List<User> obtainInformationOfAllUserList();

	@Query(value = " SELECT new com.microservice.user.importadora.entities.User(u.user, u.password) " + " FROM User u "
			+ " WHERE u.userName = ?1")
	User obtainUserAndPassword(String userName);

	@Transactional
	@Modifying
	@Query(value = "UPDATE User SET user=?1, password=?2, department_id=?3, privilege_id=?4, user_name=?5, role=?6"
			+ " WHERE user = ?1 ")
	int updateUserInformation(String user, String password, int departmentId, int privilegeId, String userName,
			String role);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM User WHERE user=?1", nativeQuery = true)
	void deleteUserByUser(String user);
	
	@Query(value = " SELECT user.user"
			+ " FROM user user",nativeQuery = true)
	List<String> userNameList();
}
