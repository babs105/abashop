package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bezkoder.spring.jwt.mongodb.models.Order;




public interface OrderRepository extends MongoRepository<Order, String>{
@Query("{'userId':?0}")
List<Order> findAllOrderByUserId(String username,Sort sort);

}
