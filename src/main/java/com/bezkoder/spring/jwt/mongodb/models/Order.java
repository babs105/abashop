package com.bezkoder.spring.jwt.mongodb.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document
public class Order {

	
	@Id
	private String id = UUID.randomUUID().toString();
	
	@NotNull
	private long numOrder;
	
	private String userId;
	private List<Product> productsOrders;
	private Address addressEmetteur;
	private Address addressExpdition;
	private double fraisExpedition;
	private double total;
	private double amount;
	private String statusPay;
	private int statusOrder;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime orderDate;
	
	private String mdp;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Product> getProductsOrders() {
		return productsOrders;
	}
	public void setProductsOrders(List<Product> productsOrders) {
		this.productsOrders = productsOrders;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Address getAddressExpdition() {
		return addressExpdition;
	}
	public void setAddressExpdition(Address addressExpdition) {
		this.addressExpdition = addressExpdition;
	}
	
	public String getStatusPay() {
		return statusPay;
	}
	public void setStatusPay(String statusPay) {
		this.statusPay = statusPay;
	}
//	public String getStatusOrder() {
//		return statusOrder;
//	}
//	public void setStatusOrder(String statusOrder) {
//		this.statusOrder = statusOrder;
//	}
	
	public Address getAddressEmetteur() {
		return addressEmetteur;
	}
	public int getStatusOrder() {
		return statusOrder;
	}
	public void setStatusOrder(int statusOrder) {
		this.statusOrder = statusOrder;
	}
	public void setAddressEmetteur(Address addressEmetteur) {
		this.addressEmetteur = addressEmetteur;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public long getNumOrder() {
		return numOrder;
	}
	public void setNumOrder(long numOrder) {
		this.numOrder = numOrder;
	}
	public double getFraisExpedition() {
		return fraisExpedition;
	}
	public void setFraisExpedition(double fraisExpedition) {
		this.fraisExpedition = fraisExpedition;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	

	
	
}
