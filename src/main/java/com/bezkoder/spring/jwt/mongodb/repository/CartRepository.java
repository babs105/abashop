package com.bezkoder.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.models.Cart;

public interface CartRepository extends MongoRepository<Cart,String>{
Cart findCartByUserId(String userId);
}
