package com.bezkoder.spring.jwt.mongodb.dto;

import java.util.Date;
import java.util.List;

import com.bezkoder.spring.jwt.mongodb.models.Caracteristique;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequest {
	private String id;
	private String name;
	private String desc;
	private String img;
	private String idFile;
//	private double price;
//	private double quantity;
//	private String size;
//	private String color;
	private List<String> categories;
	private List<Caracteristique> caract;
	private Date createdAt;
	
	
	

	
	public List<Caracteristique> getCaract() {
		return caract;
	}
	public void setCaract(List<Caracteristique> caract) {
		this.caract = caract;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getIdFile() {
		return idFile;
	}
	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
//	public double getPrice() {
//		return price;
//	}
//	public void setPrice(double price) {
//		this.price = price;
//	}
//	public double getQuantity() {
//		return quantity;
//	}
//	public void setQuantity(double quantity) {
//		this.quantity = quantity;
//	}
//
//	
//	public String getSize() {
//		return size;
//	}
//	public void setSize(String size) {
//		this.size = size;
//	}
//	public String getColor() {
//		return color;
//	}
//	public void setColor(String color) {
//		this.color = color;
//	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	
	
}
