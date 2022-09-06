package com.bezkoder.spring.jwt.mongodb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jwt.mongodb.models.Notification;
import com.bezkoder.spring.jwt.mongodb.service.NotificationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notif")
public class NotificationController {
	@Autowired
	NotificationService notificationService;

	@GetMapping("/getUserNotification/{userId}")
	@PreAuthorize("hasRole('USER')")
	public List<Notification> getUserNotification(@PathVariable String userId) {
		return notificationService.getUserNotificationFromUser(userId);
	}
	@GetMapping("/getAllNotification")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Notification> getAllNotisfication() {
		return notificationService.getAllNotification();
	}
	@RequestMapping(value = "/updateNotification", method = { RequestMethod.PUT,
			RequestMethod.OPTIONS }, produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public Notification updateNotification(@RequestBody Notification notif) {
		return notificationService.updateNotification(notif);
	}
	@RequestMapping(value = "/updateAllNotification", method = { RequestMethod.PUT,
			RequestMethod.OPTIONS }, produces = "application/json")
    @PreAuthorize("hasRole('USER')")
	public List<Notification> updateAllNotification(@RequestBody List<Notification> notifs) {
		return notificationService.saveAllNotification(notifs);
	}
}
