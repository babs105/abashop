package com.bezkoder.spring.jwt.mongodb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.jwt.mongodb.dto.ProductImgResponse;
import com.bezkoder.spring.jwt.mongodb.models.Product;

public interface ProductService {
	Product saveProduct(Product product);
	Product addProduct(MultipartFile file,String product) throws IOException ;
	Product updateProduct(MultipartFile file,String product) throws IOException ;
	Product getProductById(String id);
	List<Product> getAllProduct();
	List<Product> getAllProductImg();
	List<Product> getProductByCat(String cat);
}
