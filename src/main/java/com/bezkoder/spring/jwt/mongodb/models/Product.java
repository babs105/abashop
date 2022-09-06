package com.bezkoder.spring.jwt.mongodb.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;


@Document
public class Product {
	@Id
	private String id = UUID.randomUUID().toString();
	private String name;
	private String desc;
	private String img;
	private String idFile;
//	private double price;
//	private double quantity;
//	private String size;
//	private String color;
    private List<Caracteristique> caract;
//	private List<String> categories;
    private List<String> categories;
	
	
	@Version
	private Integer version;
	
     @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @JsonIgnoreProperties(ignoreUnknown = true)
    @CreatedDate
	private Date createdAt;
	
//    @LastModifiedDate
//    public Date updatedAt;
	
//	public List<Caracterisque> getCaracterisques() {
//		return caracterisques;
//	}
//	public void setCaracterisques(List<Caracterisque> caracterisques) {
//		this.caracterisques = caracterisques;
//	}
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
	
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
//	public double getPrice() {
//		return price;
//	}
//	public void setPrice(double price) {
//		this.price = price;
//	}
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
//	public double getQuantity() {
//		return quantity;
//	}
//	public void setQuantity(double quantity) {
//		this.quantity = quantity;
//	}


	public String getImg() {
		return img;
	}
	
	public List<Caracteristique> getCaract() {
		return caract;
	}
	public void setCaract(List<Caracteristique> caract) {
		this.caract = caract;
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

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
