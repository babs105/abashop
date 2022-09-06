package com.bezkoder.spring.jwt.mongodb.service;

import java.util.List;

import com.bezkoder.spring.jwt.mongodb.models.Notification;

public interface NotificationService {
	Notification saveNotification(Notification notif);

	List<Notification> saveAllNotification(List<Notification> notifs);

	Notification getNotificationByUserId(String userId);

	Notification getNotificationById(String id);

	Notification getNotificationByType(String type);

	// List<Notification> getUserNotification(String fromUser);

	List<Notification> getUserNotificationFromUser(String fromUser);

	List<Notification> getAllNotification();

	Notification updateNotification(Notification notif);

	Notification getNotificationByUserAndId(String userId, String id);

}
