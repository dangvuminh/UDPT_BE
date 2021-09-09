package com.dang.userService.controller;

import com.dang.userService.entity.User;
import com.dang.userService.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dang.userService.entity.UserResponse;
import com.dang.userService.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserQueryController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/validate/{id}")
	public UserResponse isUserExisted(@PathVariable String id) {
		return userService.isUserExisted(id);
	}

	@GetMapping("/get_users")
	public List<User> getUserList() {
		return userService.getUserList();
	}

	@GetMapping("/is_admin/{userId}")
	public UserResponse getAdmin(@PathVariable String userId) {
		return userService.getAdmin(userId);
	}

	@GetMapping("/get_user/{userId}")
	public Optional<UserInfo> getUser(@PathVariable String userId) {
		return userService.getUser(userId);
	}
}

