package com.bezkoder.spring.jwt.mongodb.service;

import com.bezkoder.spring.jwt.mongodb.dto.ProductToCartRequest;
import com.bezkoder.spring.jwt.mongodb.models.Cart;
import com.bezkoder.spring.jwt.mongodb.models.Product;


public interface CartService {
 Cart createCart(Cart cart);
 Cart addProductToCart(ProductToCartRequest produit);
 Cart getCartByUserId(String userId);
 Cart getCartById(String id);
 Cart updateCart(Cart cart);
 Cart removeProductToCart(ProductToCartRequest product);
 Cart resetCart(String cartId);
 void deleteCartById(String cartId);
}
