package com.bezkoder.spring.jwt.mongodb.dto;

public class CategoryRequest {
    private String id ;
	private String categoryName;
	private String title;
	private String description;
	private String type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public String getCategoryImg() {
//		return categoryImg;
//	}
//	public void setCategoryImg(String categoryImg) {
//		this.categoryImg = categoryImg;
//	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


}
