package com.dang.userService.repository;

import java.util.Optional;

import com.dang.userService.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dang.userService.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	//@Query("select * from User where user = }")
	Optional<User> findUserByUsername(String username);

	@Query("Select u from User u where u.username = ?1 and u.password = ?2")
	Optional<User> userSignInAuthen(String username, String password);
}
