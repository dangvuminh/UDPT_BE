package com.dang.userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dang.userService.entity.UserResponse;
import com.dang.userService.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserQueryController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/validate/{id}")
	public UserResponse isUserExisted(@PathVariable String id) {
		return userService.isUserExisted(id);
	}
}
