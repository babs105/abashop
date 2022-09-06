package com.bezkoder.spring.jwt.mongodb.dto;



import com.bezkoder.spring.jwt.mongodb.models.Product;

public class ProductToCartRequest {
    private Product newProduct;
//    private String id;
//	private String name;
//	private String desc;
//	private String img;
//    private double price;
//    private double quantity;
//	private String size;
//	private String color;
	private String userId;
	private String idCart;
	
	
//public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getDesc() {
//		return desc;
//	}
//	public void setDesc(String desc) {
//		this.desc = desc;
//	}
//	public String getImg() {
//		return img;
//	}
//	public void setImg(String img) {
//		this.img = img;
//	}
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
		public Product getNewProduct() {
		return newProduct;
	}
	public void setNewProduct(Product newProduct) {
		this.newProduct = newProduct;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIdCart() {
		return idCart;
	}
	public void setIdCart(String idCart) {
		this.idCart = idCart;
	}
	
	
	

}
