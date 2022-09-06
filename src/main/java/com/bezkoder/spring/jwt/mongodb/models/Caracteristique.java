package com.bezkoder.spring.jwt.mongodb.models;

public class Caracteristique {
	
	private String id;
	private double price;
	private double quantity;
	private String size;
	private String color;
	
	
	public String getId() {
		return id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

}
