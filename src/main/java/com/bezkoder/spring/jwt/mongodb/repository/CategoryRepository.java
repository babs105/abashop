package com.bezkoder.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.models.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
