package com.bezkoder.spring.jwt.mongodb.service;

import java.io.IOException;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SsePushNotificationService {
	void addEmitter(final SseEmitter emitter);
	void removeEmitter(final SseEmitter emitter);
	void doNotify() throws IOException;
}
