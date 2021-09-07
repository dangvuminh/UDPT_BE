package com.dang.userService.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dang.userService.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dang.userService.repository.UserRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	private List<User> mapUserWithComments(List<User> userList) {
		return userList.stream().map((q) -> {
			NumberOfComments num = restTemplate.getForObject("http://COMMENT-SERVICE/api/comment/num_of_comments/" + q.getUser_id(), NumberOfComments.class);
			return new User(q.getUser_id(),q.getUsername(),q.getPassword(),q.getFirst_name(),q.getLast_name(),q.getEmail(),q.getProfile_img(),q.isIs_admin(),num.getNumberOfComments());
		}).collect(Collectors.toList());
	}

	public void signUp(User user) {
		Optional<User> isUserExisted = userRepository.findUserByUsername(user.getUsername());
		if(isUserExisted.isPresent()) {
			throw new IllegalStateException("This username is not available");
		}
		user.setUser_id(UUID.randomUUID().toString());
		userRepository.save(user);
		String message = user.getEmail() + "," + "A new account from Social Network" + "," + "Congratulations! You have created a new account in Social Network.Sign in and explore our content.Thank you";
		restTemplate.getForObject("http://MAIL-SERVICE/api/mail/sendEmail" + "?msg=" + message,String.class);
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

		List<User> userList = userRepository.findAll();
		return mapUserWithComments(userList);
	}

	public UserResponse updateProfile(UpdateProfile updateProfile) {
		Optional<User> isUserExisted = userRepository.findById(updateProfile.getUserId());
		if(isUserExisted.isPresent()){
			userRepository.updateProfile(updateProfile.getUserId(),updateProfile.getFirstName(),updateProfile.getLastName(),updateProfile.getUserName(),updateProfile.getEmail());
			return  new UserResponse("updated","User profile has been updated",204);
		}
		return  new UserResponse("fail to update","User profile can't be updated.One or more input field is invalid",404);
	}

	public UserResponse changePassword(ChangePassword changePassword) {
		Optional<User> user = userRepository.findById(changePassword.getUserId());
		if(user.isPresent() && user.get().getPassword().equals(changePassword.getOldPassword())) {
			userRepository.changePassword(changePassword.getUserId(),changePassword.getNewPassword());
			return new UserResponse("password changed","You have changed the password",204);
		}
		return new UserResponse("failed to change","You can't changed the password.User or password are invalid",403);
	}

	public void changeImage(ChangeImage changeImage) {
		userRepository.changeImage(changeImage.getUserId(),changeImage.getImgUrl());
	}

	public UserResponse getAdmin(String userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent() && user.get().isIs_admin() == true) {
			return new UserResponse("is admin","This user is Admin",204);
		}
		return new UserResponse("not found admin","This user is not Admin",404);
	}
}
