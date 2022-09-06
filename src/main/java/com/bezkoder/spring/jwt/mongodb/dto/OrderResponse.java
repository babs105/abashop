package com.bezkoder.spring.jwt.mongodb.dto;

import com.bezkoder.spring.jwt.mongodb.models.Order;
import com.bezkoder.spring.jwt.mongodb.models.User;

public class OrderResponse {
	
	private Order order;
	private User user;
	private boolean error;
	private String message;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
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
