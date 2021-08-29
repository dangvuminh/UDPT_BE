package com.dang.userService.controller;

import com.dang.userService.entity.UserInfo;
import com.dang.userService.entity.UserSignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dang.userService.entity.User;
import com.dang.userService.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserCommandController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signUp")
	public void signUp(@RequestBody User user) {
		userService.signUp(user);
	}

	@PostMapping("/signIn")
	public Optional<UserInfo> signIn(@RequestBody UserSignIn user) {
		return userService.signIn(user);
	}
}
