package com.bezkoder.spring.jwt.mongodb.controllers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.bezkoder.spring.jwt.mongodb.models.Notification;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.security.services.UserDetailsImpl;
import com.bezkoder.spring.jwt.mongodb.service.SsePushNotificationService;
import com.bezkoder.spring.jwt.mongodb.service.UserService;
import com.bezkoder.spring.jwt.mongodb.serviceImpl.SSEServiceImpl;
// import com.bezkoder.spring.jwt.mongodb.serviceImpl.SsePushNotificationServiceImpl;

import reactor.core.publisher.Flux;

//
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/push")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class SsePushNotificationController {
	private static final Logger logger = LoggerFactory.getLogger(SsePushNotificationController.class);
	// @Autowired
	// SsePushNotificationService ssePushNotificationService;
	// @Autowired
	// UserService userService;

	@Autowired
	private SSEServiceImpl sseService;
	// final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	//
	// private int lastId = 0;
	// @GetMapping("/notification")
	// @PreAuthorize("hasRole('USER')")
	// public ResponseEntity<SseEmitter> doNotify(@RequestParam("userId") String
	// userId) throws InterruptedException, IOException {
	// final SseEmitter emitter = new SseEmitter();
	// logger.info("user auth :"+userId);
	// ssePushNotificationService.addEmitter(emitter);
	// ssePushNotificationService.doNotify();
	// emitter.onCompletion(() ->
	// ssePushNotificationService.removeEmitter(emitter));
	// emitter.onTimeout(() -> ssePushNotificationService.removeEmitter(emitter));
	// return new ResponseEntity<>(emitter, HttpStatus.OK);
	// }
	// @Async
	// @Scheduled(fixedRate = 5000)
	// public void heartbeat() throws IOException {
	// SseEmitter emitter = new SseEmitter();
	//
	// logger.info("schrjjj");
	// emitter.send(SseEmitter.event()
	// .name("heartbeat")
	// .id("" + ++lastId)
	// .data("heartbeat"));
	//

	// }
	@GetMapping("/getAllUserNotifs")
	public Flux<ServerSentEvent<List<Notification>>> streamNotifications(@RequestParam("userId") String userId) {
		logger.info("user auth :" + userId);

		return sseService.getAllUserNotication(userId);
	}

	@GetMapping("/getAllNotifs")
	public Flux<ServerSentEvent<List<Notification>>> streamAllNotifications() {
		logger.info("user auth Admin ");
		return sseService.getAllNotication();
	}

}
