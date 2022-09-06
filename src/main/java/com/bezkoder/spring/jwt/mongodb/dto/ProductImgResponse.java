package com.bezkoder.spring.jwt.mongodb.dto;

import java.util.List;

import com.bezkoder.spring.jwt.mongodb.models.Caracteristique;

public class ProductImgResponse {
	private String id;
	private String name;
	private String desc;
	private String img;
	private String idFile;
    private List<Caracteristique> caract;
    private List<String> categories;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public List<Caracteristique> getCaract() {
		return caract;
	}
	public void setCaract(List<Caracteristique> caract) {
		this.caract = caract;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public String getIdFile() {
		return idFile;
	}
	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}

}
