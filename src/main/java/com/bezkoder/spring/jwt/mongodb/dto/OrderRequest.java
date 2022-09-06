package com.bezkoder.spring.jwt.mongodb.dto;

import com.bezkoder.spring.jwt.mongodb.models.Order;

public class OrderRequest {
	private String idCart;
	private String password;
	private Order order;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdCart() {
		return idCart;
	}
	public void setIdCart(String idCart) {
		this.idCart = idCart;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
