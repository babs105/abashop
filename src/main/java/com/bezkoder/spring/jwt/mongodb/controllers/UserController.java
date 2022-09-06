package com.bezkoder.spring.jwt.mongodb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jwt.mongodb.dto.ProfileRequest;
import com.bezkoder.spring.jwt.mongodb.dto.ProfileResponse;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.payload.request.SignupRequest;
import com.bezkoder.spring.jwt.mongodb.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
	final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@GetMapping("/userDetail")
	@PreAuthorize("hasRole('USER')")
	public User userDetail() {
		return userService.getLoggedUserDetails();
	}

	@PostMapping("/editUserDetail")
	public User saveUserDetail(@Valid @RequestBody SignupRequest signUpRequest) {
		return userService.editUserDetail(signUpRequest);
	}

	@GetMapping("/getAllUsers")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/getUserById/{id}", method = { RequestMethod.GET,
			RequestMethod.OPTIONS }, produces = "application/json")
	// @PreAuthorize("hasRole('ADMIN')")
	public User getUserById(@PathVariable String id) {
		return userService.getUserById(id);
	}

	@RequestMapping(value = "/updateUser", method = { RequestMethod.PUT,
			RequestMethod.OPTIONS }, produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public User updateUser(@RequestBody SignupRequest signUpRequest) {
		return userService.updateUser(signUpRequest);
	}

	@RequestMapping(value = "/updateUserProfile", method = { RequestMethod.PUT,
			RequestMethod.OPTIONS }, produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public ProfileResponse updateUserProfile(@RequestBody ProfileRequest profileRequest) {
		return userService.updateUserProfile(profileRequest);
	}

}
