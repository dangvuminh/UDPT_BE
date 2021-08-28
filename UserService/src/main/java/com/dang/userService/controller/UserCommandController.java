package com.dang.userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dang.userService.entity.User;
import com.dang.userService.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserCommandController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signUp")
	public void signUp(@RequestBody User user) {
		userService.signUp(user);
	}
}
