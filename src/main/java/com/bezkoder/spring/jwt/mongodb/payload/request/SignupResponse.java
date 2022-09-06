package com.bezkoder.spring.jwt.mongodb.payload.request;


import com.bezkoder.spring.jwt.mongodb.models.User;

public class SignupResponse {

	private User user;
	private boolean error;
	private String message;
	

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
