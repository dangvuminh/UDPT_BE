package com.dang.userService.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.dang.userService.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dang.userService.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	public void signUp(User user) {
		Optional<User> isUserExisted = userRepository.findUserByUsername(user.getUsername());
		if(isUserExisted.isPresent()) {
			throw new IllegalStateException("This username is not available");
		}
		user.setUser_id(UUID.randomUUID().toString());
		userRepository.save(user);
	}

	public Optional<UserInfo> signIn(UserSignIn user){
		Optional<User> userInfo = userRepository.userSignInAuthen(user.getUsername(),user.getPassword());
		if(userInfo.isPresent()) {
			return Optional.of(new UserInfo(userInfo.get().getUser_id(),
					userInfo.get().getUsername(),
					userInfo.get().getFirst_name(),
					userInfo.get().getLast_name(),
					userInfo.get().getEmail(),
					userInfo.get().getProfile_img(),
					userInfo.get().isIs_admin()));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User or password not correct");
	}
	
	public UserResponse isUserExisted(String id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return new UserResponse("user found","The user can be used",204);
		}
		return new UserResponse("user not found","The user is not existing",404);
	}

	public List<User> getUserList() {
		return userRepository.findAll();
	}

	public UserResponse updateProfile(UpdateProfile updateProfile) {
		Optional<User> isUserExisted = userRepository.findById(updateProfile.getUserId());
		if(isUserExisted.isPresent()){
			userRepository.updateProfile(updateProfile.getUserId(),updateProfile.getFirstName(),updateProfile.getLastName(),updateProfile.getUserName(),updateProfile.getEmail());
			return  new UserResponse("updated","User profile has been updated",204);
		}
		return  new UserResponse("fail to update","User profile can't be updated.One or more input field is invalid",404);
	}
}
