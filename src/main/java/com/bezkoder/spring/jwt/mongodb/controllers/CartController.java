package com.bezkoder.spring.jwt.mongodb.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jwt.mongodb.dto.ProductToCartRequest;
import com.bezkoder.spring.jwt.mongodb.models.Cart;
import com.bezkoder.spring.jwt.mongodb.models.Product;
import com.bezkoder.spring.jwt.mongodb.service.CartService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Autowired 
	CartService cartService;
	
	@RequestMapping(value = "/addProductToCart", method ={ RequestMethod.POST,RequestMethod.OPTIONS } ,produces = "application/json")
	public Cart addProductToCart(@RequestBody ProductToCartRequest produitRequest ) { 
		return cartService.addProductToCart(produitRequest);
	}

	@RequestMapping(value = "/getCartByUserId/{userId}", method = { RequestMethod.GET,RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Cart getCartByUserId(@PathVariable String userId) {
		logger.info("IN GET CART BY USERID");
		return cartService.getCartByUserId(userId);
	}
	
	
	@RequestMapping(value = "/getCartById/{idCart}", method = { RequestMethod.GET,RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Cart getCartById(@PathVariable String idCart) {
		return cartService.getCartById(idCart);
	}
	@RequestMapping(value = "/updateCart", method ={ RequestMethod.PUT,RequestMethod.OPTIONS } ,produces = "application/json")
	public Cart updateCart(@RequestBody Cart cart ) { 
		return cartService.updateCart(cart);
	}

	@RequestMapping(value = "/removeProductToCart", method ={ RequestMethod.POST,RequestMethod.OPTIONS } ,produces = "application/json")
	public Cart removeProductToCart(@RequestBody ProductToCartRequest product ) { 
		return cartService.removeProductToCart(product);
	}
	@RequestMapping(value = "/resetCart/{idCart}", method ={ RequestMethod.GET,RequestMethod.OPTIONS } ,produces = "application/json")
	public Cart removeAllProductToCart(@PathVariable String cartId  ) { 
		return cartService.resetCart(cartId);
	}
	
}
