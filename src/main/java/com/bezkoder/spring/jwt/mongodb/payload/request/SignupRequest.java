package com.bezkoder.spring.jwt.mongodb.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
	@NotBlank
	@Size(max = 50)
	private String username;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	// private String idCart;
	private String address;
	private String telephone;
	private String firstName;
	private String lastName;

	private Set<String> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return this.roles;
	}

	public void setRole(Set<String> roles) {
		this.roles = roles;
	}

	// public String getIdCart() {
	// return idCart;
	// }

	// public void setIdCart(String idCart) {
	// this.idCart = idCart;
	// }

}
