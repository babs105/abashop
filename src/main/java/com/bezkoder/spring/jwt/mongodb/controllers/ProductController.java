package com.bezkoder.spring.jwt.mongodb.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.jwt.mongodb.dto.LoadFile;
import com.bezkoder.spring.jwt.mongodb.models.Product;
import com.bezkoder.spring.jwt.mongodb.service.FileService;
import com.bezkoder.spring.jwt.mongodb.service.ProductService;
import com.bezkoder.spring.jwt.mongodb.serviceImpl.ProductServiceImpl;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
    @Autowired
    ProductService productService;
    
    @Autowired
    FileService fileService;
	
//    @PreAuthorize("hasRole('ADMIN')")

	@RequestMapping(value = "/create", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
    @PreAuthorize("hasRole('ADMIN')")
	public Product addProduct(@RequestParam(value="imgProduct") MultipartFile imgProduct, @RequestParam("product") String product) throws IOException { 
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		logger.info("user details",auth.getDetails());

		
		return productService.addProduct(imgProduct, product);
	}
	
	@GetMapping("/getAllProduct")
//   @PreAuthorize("hasRole('ADMIN')")
	public List<Product> getAllProduct() {
	return productService.getAllProduct();
//		return productService.getAllProductImg();
	}
	@RequestMapping(value = "/getProductByCat/{cat}", method = { RequestMethod.GET,RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
//  @GetMapping("/getProductByCat/{cat}")
//	@PreAuthorize("hasRole('ADMIN')")
	public List<Product> getProductByCat(@PathVariable String cat) {
		return productService.getProductByCat(cat);
	}
	
	@RequestMapping(value = "/getProductById/{id}", method = { RequestMethod.GET,RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Product getProductById(@PathVariable String id) {
		return productService.getProductById(id);
	}
	
	
	@RequestMapping(value = "/updateProduct", method = RequestMethod.PUT,consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
//	@PreAuthorize("hasRole('ADMIN')")
	public Product updateProduct(@RequestParam(value="imgProduct",required = false) MultipartFile file, @RequestParam("product") String product) throws IOException {
		return productService.updateProduct(file,product);
	}
	
   @GetMapping("/download/{id}")
	    public ResponseEntity<?> download(@PathVariable String id) throws IOException {
	        LoadFile loadFile = fileService.downloadFile(id);

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(loadFile.getFileType() ))
//	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
	                .body((Base64.getEncoder().encodeToString(loadFile.getFile())));
	    }
}
