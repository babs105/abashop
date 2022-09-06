package com.bezkoder.spring.jwt.mongodb.service;

import java.util.List;

import com.bezkoder.spring.jwt.mongodb.dto.OrderRequest;
import com.bezkoder.spring.jwt.mongodb.dto.OrderResponse;
import com.bezkoder.spring.jwt.mongodb.models.Order;

public interface OrderService {
    Order saveOrder(Order order);
	OrderResponse addOrder(OrderRequest orderRequest);
	Order getOrderById(String id);
	Order updateOrder(Order order);
	List<Order> getAllOrders();
	 List<Order> getAllOrdersByUserId(String userId);
	

}
