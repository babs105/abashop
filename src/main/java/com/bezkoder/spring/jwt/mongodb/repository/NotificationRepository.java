package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bezkoder.spring.jwt.mongodb.models.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

  Notification findByFromUser(String userid);

  // List<Notification> findAllBy(String userId);

  Notification findByFromUserAndId(String userId, String id);

  Notification findByType(String id);

  // @Query("{'userId':?0,'type':?1}")

  @Query("{'fromUser':?0}")
  List<Notification> findAllFromUser(String fromUser, Sort sort);
}
