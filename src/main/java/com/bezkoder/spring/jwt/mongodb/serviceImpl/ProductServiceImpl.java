package com.bezkoder.spring.jwt.mongodb.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.jwt.mongodb.dto.LoadFile;
import com.bezkoder.spring.jwt.mongodb.dto.ProductRequest;
import com.bezkoder.spring.jwt.mongodb.models.Notification;
import com.bezkoder.spring.jwt.mongodb.models.Product;
import com.bezkoder.spring.jwt.mongodb.repository.ProductRepository;
import com.bezkoder.spring.jwt.mongodb.service.FileService;
import com.bezkoder.spring.jwt.mongodb.service.NotificationService;
import com.bezkoder.spring.jwt.mongodb.service.ProductService;
import com.bezkoder.spring.jwt.mongodb.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	FileService fileService;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	UserService userService;

	@Autowired
	NotificationService notificationService;

	@Override
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public Product addProduct(MultipartFile file, String product) throws IOException {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("user ADD PRODUCT", auth.getDetails());

		ObjectMapper mapper = new ObjectMapper();
		ProductRequest productRequest = mapper.readValue(product, ProductRequest.class);
		Product productToCreate = new Product();

		if (file != null) {
			productToCreate.setIdFile(fileService.addFile(file));
			LoadFile loadFile = fileService.downloadFile(productToCreate.getIdFile());
			productToCreate.setImg((Base64.getEncoder().encodeToString(loadFile.getFile())));
		}
		productToCreate.setName(productRequest.getName());
		productToCreate.setDesc(productRequest.getDesc());
		productToCreate.setCaract(productRequest.getCaract());
		productToCreate.setCategories(productRequest.getCategories());

		productToCreate = saveProduct(productToCreate);
		if (productToCreate != null) {
			Notification notif = new Notification(
					"Nouveau Produit",
					"Un nouveau Produit a ete ajoute",
					"",
					"all",
					productToCreate.getId());
			notificationService.saveNotification(notif);
		}

		return saveProduct(productToCreate);
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product updateProduct(MultipartFile file, String product) throws IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		ProductRequest productRequest = mapper.readValue(product, ProductRequest.class);
		Product productToUpdate = getProductById(productRequest.getId());

		if (file != null) {
			productToUpdate.setIdFile(fileService.addFile(file));
			LoadFile loadFile = fileService.downloadFile(productToUpdate.getIdFile());
			productToUpdate.setImg((Base64.getEncoder().encodeToString(loadFile.getFile())));
		}
		productToUpdate.setName(productRequest.getName());
		productToUpdate.setDesc(productRequest.getDesc());
		productToUpdate.setCaract(productRequest.getCaract());
		productToUpdate.setCategories(productRequest.getCategories());
		return saveProduct(productToUpdate);
	}

	@Override
	public Product getProductById(String id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).get();
	}

	@Override
	public List<Product> getProductByCat(String cat) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("categories").in(cat));
		List<Product> productByCat = mongoTemplate.find(query, Product.class);
		return productByCat;
	}

	@Override
	public List<Product> getAllProductImg() {
		// TODO Auto-generated method stub

		List<Product> allproducts = getAllProduct();
		List<Product> productImgs = new ArrayList<>();

		allproducts.forEach(product -> {
			try {
				product.setImg(
						Base64.getEncoder().encodeToString(fileService.downloadFile(product.getIdFile()).getFile()));
				productImgs.add(product);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		return productImgs;

	}

}
