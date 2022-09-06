package com.bezkoder.spring.jwt.mongodb.models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Category {
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	private String categoryName;
	private String title;
	private String description;
	private String categoryImg;
	private String type;
	private String idFile;
//	private List<String> productIds;



	public String getCategoryName() {
		return categoryName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdFile() {
		return idFile;
	}

	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public List<String> getProductIds() {
//		return productIds;
//	}
//
//	public void setProductIds(List<String> productIds) {
//		this.productIds = productIds;
//	}

	public String getCategoryImg() {
		return categoryImg;
	}

	public void setCategoryImg(String categoryImg) {
		this.categoryImg = categoryImg;
	}



}
