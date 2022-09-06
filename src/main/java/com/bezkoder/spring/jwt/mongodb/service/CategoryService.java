package com.bezkoder.spring.jwt.mongodb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.jwt.mongodb.models.Category;

public interface CategoryService {
Category saveCategory(Category cat);
Category addCategory(MultipartFile file,String category) throws IOException ;
Category updateCategory(MultipartFile file,String Category) throws IOException ;
Category getCategoryById(String id);
List<Category> getAllCategory();
//List<Category> getCategoryByCat(String cat);
}
