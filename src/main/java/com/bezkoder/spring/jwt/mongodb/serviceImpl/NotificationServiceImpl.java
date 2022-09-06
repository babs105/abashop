package com.bezkoder.spring.jwt.mongodb.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.bezkoder.spring.jwt.mongodb.models.Notification;
import com.bezkoder.spring.jwt.mongodb.repository.NotificationRepository;
import com.bezkoder.spring.jwt.mongodb.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	NotificationRepository notificationRepository;

	@Override
	public Notification saveNotification(Notification notif) {
		// TODO Auto-generated method stub
		try {
			return notificationRepository.save(notif);
		} catch (Exception e) {
			logger.error("Exception occur while save Notification ", e);
			return null;
		}
	}

	@Override
	public Notification getNotificationByUserId(String userId) {
		// TODO Auto-generated method stub
		try {
			return notificationRepository.findByFromUser(userId);
		} catch (Exception e) {
			logger.error("Exception occur while fetch Notification by User ", e);
			return null;
		}
	}

	// @Override
	// public List<Notification> getUserNotification(String userid) {
	// // TODO Auto-generated method stub
	// try {
	// return notificationRepository.findAllByUserId(userid);
	// } catch (Exception e) {
	// logger.error("Exception occur while fetch Notification by User ", e);
	// return null;
	// }
	// }

	@Override
	public Notification getNotificationByUserAndId(String userId, String id) {
		// TODO Auto-generated method stub
		try {
			return notificationRepository.findByFromUserAndId(userId, id);
		} catch (Exception e) {
			logger.error("Exception occur while fetch Notification by User and Notification Id ", e);
			return null;
		}
	}

	// public Notification createNotificationObject(String title,String
	// message,String type,String userId){
	// return new Notification(title,message,type,userId);
	// }

	@Override
	public List<Notification> getAllNotification() {
		// TODO Auto-generated method stub
		try {
			Sort sort = Sort.by(Direction.DESC, "dateNotif");
			return notificationRepository.findAll(sort);
		} catch (Exception e) {
			logger.error("Exception occur while fetch all Notification ", e);
			return null;
		}

	}

	@Override
	public Notification updateNotification(Notification notif) {
		// TODO Auto-generated method stub
		notif = saveNotification(notif);
		if (notif != null) {
			return notif;
		}
		return null;
	}

	@Override
	public List<Notification> getUserNotificationFromUser(String fromUser) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Direction.DESC, "dateNotif");
		return notificationRepository.findAllFromUser(fromUser, sort);
	}

	@Override
	public List<Notification> saveAllNotification(List<Notification> notifs) {
		// TODO Auto-generated method stub
		return notificationRepository.saveAll(notifs);
	}

	@Override
	public Notification getNotificationById(String id) {
		// TODO Auto-generated method stub
		return notificationRepository.findById(id).get();
	}

	@Override
	public Notification getNotificationByType(String type) {
		// TODO Auto-generated method stub
		return notificationRepository.findByType(type);
	}
}
