package com.dang.userService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dang.userService.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	//@Query("select * from User where user = }")
	Optional<User> findUserByUsername(String username);
	
}
