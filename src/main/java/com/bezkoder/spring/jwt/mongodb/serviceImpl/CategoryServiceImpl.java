package com.bezkoder.spring.jwt.mongodb.serviceImpl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.jwt.mongodb.dto.CategoryRequest;
import com.bezkoder.spring.jwt.mongodb.dto.LoadFile;
import com.bezkoder.spring.jwt.mongodb.models.Category;
import com.bezkoder.spring.jwt.mongodb.repository.CategoryRepository;
import com.bezkoder.spring.jwt.mongodb.service.CategoryService;
import com.bezkoder.spring.jwt.mongodb.service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	FileService fileService;  
	

	@Override
	public Category saveCategory(Category cat) {
		// TODO Auto-generated method stub
		return categoryRepository.save(cat);
	}

	@Override
	public Category addCategory(MultipartFile file, String category) throws IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		CategoryRequest categoryRequest = mapper.readValue(category, CategoryRequest.class);
		Category categoryToCreate  = new Category();
		   
		if(file != null ) {
			categoryToCreate.setIdFile(fileService.addFile(file));
			LoadFile loadFile = fileService.downloadFile(categoryToCreate.getIdFile());
			categoryToCreate.setCategoryImg(Base64.getEncoder().encodeToString(loadFile.getFile()));
		}
		
		categoryToCreate.setCategoryName(categoryRequest.getCategoryName());
		categoryToCreate.setDescription(categoryRequest.getDescription());
		categoryToCreate.setTitle(categoryRequest.getTitle());
		categoryToCreate.setType(categoryRequest.getType());
		
		return saveCategory(categoryToCreate);
	}

	@Override
	public Category updateCategory(MultipartFile file, String Category) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getCategoryById(String id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).get();
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

}
