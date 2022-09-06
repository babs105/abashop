package com.bezkoder.spring.jwt.mongodb.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jwt.mongodb.controllers.UserController;
import com.bezkoder.spring.jwt.mongodb.dto.ProductToCartRequest;
import com.bezkoder.spring.jwt.mongodb.models.Cart;
import com.bezkoder.spring.jwt.mongodb.models.Product;
import com.bezkoder.spring.jwt.mongodb.repository.CartRepository;
import com.bezkoder.spring.jwt.mongodb.service.CartService;


@Service
public class CartServiceImpl implements CartService {
	final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	CartRepository cartRepository;

	@Override
	public Cart createCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartRepository.save(cart);
	}

	@Override
	public Cart addProductToCart(ProductToCartRequest produitRequest) {
		// TODO Auto-generated method stub
//		UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(produitRequest.getIdCart()== null) {
			Cart newCart = new Cart(); 
			newCart.setQuantity(0);
			newCart.setTotal(0);
			List<Product> newProducts = new ArrayList<>();
			newProducts.add(produitRequest.getNewProduct());
			newCart.setProducts(newProducts);
			newCart.setQuantity(newCart.getQuantity() + 1);
			newCart.setTotal(newCart.getTotal() + produitRequest.getNewProduct().getCaract().get(0).getPrice() * produitRequest.getNewProduct().getCaract().get(0).getQuantity());
			return  createCart(newCart);	
		}
		else {
			Cart myCart = getCartById(produitRequest.getIdCart());
			if(myCart.getProducts().isEmpty()) {
				myCart.getProducts().add(produitRequest.getNewProduct());
				myCart.setQuantity(myCart.getQuantity() + 1);
				myCart.setTotal(myCart.getTotal() + produitRequest.getNewProduct().getCaract().get(0).getPrice() * produitRequest.getNewProduct().getCaract().get(0).getQuantity());
			}else {
				boolean isPresent = false;
				for(int i=0;i<myCart.getProducts().size();i++) {
					if(myCart.getProducts().get(i).getId().equals(produitRequest.getNewProduct().getId())) {
						isPresent = true ;
						break;
					}
				}
				if(isPresent == false) {
					myCart.getProducts().add(produitRequest.getNewProduct());
					myCart.setQuantity(myCart.getQuantity() + 1);
					myCart.setTotal(myCart.getTotal() + produitRequest.getNewProduct().getCaract().get(0).getPrice() * produitRequest.getNewProduct().getCaract().get(0).getQuantity());
				}
			}
			
			return cartRepository.save(myCart);
		}
	}

	@Override
	public Cart getCartByUserId(String userId) {
		// TODO Auto-generated method stub
		Cart cartuser = cartRepository.findCartByUserId(userId);
		logger.info("CART USER" ,cartuser.getId());
		return cartRepository.findCartByUserId(userId);
	}

	@Override
	public Cart getCartById(String id) {
		// TODO Auto-generated method stub
		return cartRepository.findById(id).get();
	}

	@Override
	public Cart updateCart(Cart cart) {
		// TODO Auto-generated method stub
		Cart cartToUpdate = getCartById(cart.getId());
		     cartToUpdate.setQuantity(cart.getQuantity());
		     cartToUpdate.setFraisExpedition(cart.getFraisExpedition());
		     cartToUpdate.setTotal(cart.getTotal());
		     cartToUpdate.setProducts(cart.getProducts());
		return  cartRepository.save(cartToUpdate);
	}

	@Override
	public Cart removeProductToCart(ProductToCartRequest product) {
		// TODO Auto-generated method stub
		Cart myCart = getCartById(product.getIdCart());
		
		for (int i=0;i<myCart.getProducts().size();i++) {
			
			if(myCart.getProducts().get(i).getId().equals(product.getNewProduct().getId())) {
				myCart.getProducts().remove(i);
				myCart.setQuantity(myCart.getQuantity() - 1);
				myCart.setTotal(myCart.getTotal() - product.getNewProduct().getCaract().get(0).getPrice() * product.getNewProduct().getCaract().get(0).getQuantity());
			}
		}
		return cartRepository.save(myCart);
	}

	@Override
	public Cart resetCart(String cartId) {
		// TODO Auto-generated method stub
		Cart myCart = getCartById(cartId);
		List<Product> emptyList = new ArrayList<Product>();
		
		
		myCart.setProducts(emptyList);
		myCart.setQuantity(0);
		myCart.setTotal(0);
		myCart.setFraisExpedition(0);
	
		return createCart(myCart);
	}

	@Override
	public void deleteCartById(String cartId) {
		// TODO Auto-generated method stu
		
		cartRepository.deleteById(cartId);
	}

}
