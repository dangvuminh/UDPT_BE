package com.dang.userService.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dang.userService.entity.User;
import com.dang.userService.entity.UserResponse;
import com.dang.userService.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	public void signUp(User user) {
		Optional<User> isUserExisted = userRepository.findUserByUsername(user.getUsername());
		if(isUserExisted.isPresent()) {
			throw new IllegalStateException("This username is not availlable");
		}
		user.setUser_id(UUID.randomUUID().toString());
		userRepository.save(user);
	}
	
	public UserResponse isUserExisted(String id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return new UserResponse("user found","The user can be used","204");
		}
		return new UserResponse("user not found","The user is not existing","404");
	}
}
