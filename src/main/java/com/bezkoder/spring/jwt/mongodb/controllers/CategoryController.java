package com.bezkoder.spring.jwt.mongodb.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.jwt.mongodb.models.Category;
import com.bezkoder.spring.jwt.mongodb.service.CategoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    
	@RequestMapping(value = "/create", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
	public Category addCategory(@RequestParam(value="imgCategory") MultipartFile imgCategory, @RequestParam("category") String category) throws IOException { 
		return categoryService.addCategory(imgCategory, category);
	}
	
	@GetMapping("/getAllCategory")
	public List<Category> getAllCategory() {
	return categoryService.getAllCategory();
//		return productService.getAllProductImg();
	}

	
	@RequestMapping(value = "/getCategoryById/{id}", method = { RequestMethod.GET,RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Category getCategoryById(@PathVariable String id) {
		return categoryService.getCategoryById(id);
	}
	
}
