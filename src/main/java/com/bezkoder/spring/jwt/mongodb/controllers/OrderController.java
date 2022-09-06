package com.bezkoder.spring.jwt.mongodb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jwt.mongodb.dto.OrderRequest;
import com.bezkoder.spring.jwt.mongodb.dto.OrderResponse;
import com.bezkoder.spring.jwt.mongodb.models.Order;
import com.bezkoder.spring.jwt.mongodb.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/create", method = { RequestMethod.POST,
			RequestMethod.OPTIONS }, produces = "application/json")
	public OrderResponse addOrder(@RequestBody OrderRequest orderRequest) {
		return orderService.addOrder(orderRequest);
	}

	@GetMapping("/getAllOrders")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@RequestMapping(value = "/updateOrder", method = { RequestMethod.PUT,
			RequestMethod.OPTIONS }, produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public Order update(@RequestBody Order Order) {
		return orderService.updateOrder(Order);
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/getOrderById/{id}", method = { RequestMethod.GET,
			RequestMethod.OPTIONS }, produces = "application/json")
	public Order getOrderById(@PathVariable String id) {
		return orderService.getOrderById(id);
	}

	@RequestMapping(value = "/getAllOrdersByUserId/{userId}", method = { RequestMethod.GET,
			RequestMethod.OPTIONS }, produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public List<Order> getAllOrdersByUserId(@PathVariable String userId) {
		return orderService.getAllOrdersByUserId(userId);
	}
}
