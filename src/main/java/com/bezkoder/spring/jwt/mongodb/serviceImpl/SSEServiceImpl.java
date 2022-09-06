package com.bezkoder.spring.jwt.mongodb.serviceImpl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import com.bezkoder.spring.jwt.mongodb.models.Notification;
import com.bezkoder.spring.jwt.mongodb.service.NotificationService;

@Service
public class SSEServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(SSEServiceImpl.class);

	@Autowired
	NotificationService notificationService;

	public Flux<ServerSentEvent<List<Notification>>> getAllUserNotication(String userId) {
		if (userId != null && !userId.isEmpty()) {
			logger.info("notif");
			List<Notification> notifs = notificationService.getUserNotificationFromUser(userId);
			return Flux.interval(Duration.ofSeconds(8)).map(sequence -> ServerSentEvent.<List<Notification>>builder()
					.id(String.valueOf(sequence)).event("all-notifUser-event").data(notifs).build());
		}

		return Flux.interval(Duration.ofSeconds(8)).map(sequence -> ServerSentEvent.<List<Notification>>builder()
				.id(String.valueOf(sequence)).event("all-notifUser-event").data(new ArrayList<>()).build());
	}

	public Flux<ServerSentEvent<List<Notification>>> getAllNotication() {
		List<Notification> notifs = notificationService.getAllNotification();
		if (notifs != null) {
			logger.info("all notif");
			// List<Notification> notifs = notificationService.getAllNotification();
			return Flux.interval(Duration.ofSeconds(8)).map(sequence -> ServerSentEvent.<List<Notification>>builder()
					.id(String.valueOf(sequence)).event("all-notifAdmin-event").data(notifs).build());
		}

		return Flux.interval(Duration.ofSeconds(8)).map(sequence -> ServerSentEvent.<List<Notification>>builder()
				.id(String.valueOf(sequence)).event("all-notifAdmin-event").data(new ArrayList<>()).build());
	}
}