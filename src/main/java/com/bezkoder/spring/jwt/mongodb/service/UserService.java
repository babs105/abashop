package com.bezkoder.spring.jwt.mongodb.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bezkoder.spring.jwt.mongodb.dto.ProfileRequest;
import com.bezkoder.spring.jwt.mongodb.dto.ProfileResponse;
import com.bezkoder.spring.jwt.mongodb.models.Cart;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.payload.request.SignupRequest;
import com.bezkoder.spring.jwt.mongodb.payload.request.SignupResponse;

public interface UserService {
	User getLoggedUserDetails();
    User getUserByUsername(String username);
	User editUserDetail(SignupRequest signUpRequest);
	User updateUser(SignupRequest signUpRequest);
	ProfileResponse updateUserProfile(ProfileRequest profileRequest);
	SignupResponse registerUser(SignupRequest signUpRequest);
	List<User> getAllUsers();
	User getUserById(String id);



}
