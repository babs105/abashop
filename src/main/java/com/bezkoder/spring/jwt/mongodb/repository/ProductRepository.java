package com.bezkoder.spring.jwt.mongodb.repository;




import org.springframework.data.mongodb.repository.MongoRepository;


import com.bezkoder.spring.jwt.mongodb.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
//@Query("{'categories': {$in:?0}}")
// List<Product> findProductByCategories(String cats);
}
