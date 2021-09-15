package com.dang.userService.controller;

import com.dang.userService.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dang.userService.service.UserService;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserCommandController {
	
	@Autowired
	UserService userService;

	@Transactional
	@PostMapping("/signUp")
	public void signUp(@RequestBody User user) {
		userService.signUp(user);
	}

	@PostMapping("/signIn")
	public Optional<UserInfo> signIn(@RequestBody UserSignIn user) {
		return userService.signIn(user);
	}

	@PutMapping("/update_profile")
	public UserResponse updateProfile(@RequestBody UpdateProfile updateProfile) {
		return userService.updateProfile(updateProfile);
	}

	@PutMapping("/change_password")
	public UserResponse changePassword(@RequestBody ChangePassword changePassword) {
		return userService.changePassword(changePassword);
	}

	@PutMapping("/change_image")
	public void changeImage(@RequestBody ChangeImage changeImage) {
		 userService.changeImage(changeImage);
	}

	@DeleteMapping("/delete_user")
	public UserResponse deleteUser(@RequestBody DeleteUser deleteUser ) {
		return userService.deleteUser(deleteUser);
	}
}
