package com.bezkoder.spring.jwt.mongodb.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Notification {
	@Id
	private String id = UUID.randomUUID().toString();

	private String title;
	private String message;
	private String fromUser;
	private String toUser;
	private String type;
	private List<String> readerUsers;

	// @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	//// @JsonIgnoreProperties(ignoreUnknown = true)
	// @CreatedDate
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dateNotif;
	private boolean isRead;

	public Notification(String title, String message, String fromUser, String toUser, String type) {
		this.title = title;
		this.message = message;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.type = type;
		this.isRead = false;
		this.dateNotif = LocalDateTime.now();
		this.readerUsers = new ArrayList<String>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// public LocalDateTime getCreatedAt() {
	// return createdAt;
	// }
	// public void setCreatedAt(LocalDateTime createdAt) {
	// this.createdAt = createdAt;
	// }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getReaderUsers() {
		return readerUsers;
	}

	public void setReaderUsers(List<String> readerUsers) {
		this.readerUsers = readerUsers;
	}

	public boolean isRead() {
		return isRead;
	}

	public LocalDateTime getDateNotif() {
		return dateNotif;
	}

	public void setDateNotif(LocalDateTime dateNotif) {
		this.dateNotif = dateNotif;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

}
