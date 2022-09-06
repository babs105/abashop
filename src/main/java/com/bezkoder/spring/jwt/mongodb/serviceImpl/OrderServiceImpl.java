package com.bezkoder.spring.jwt.mongodb.serviceImpl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jwt.mongodb.dto.OrderRequest;
import com.bezkoder.spring.jwt.mongodb.dto.OrderResponse;
import com.bezkoder.spring.jwt.mongodb.models.Cart;
import com.bezkoder.spring.jwt.mongodb.models.DatabaseSequence;
import com.bezkoder.spring.jwt.mongodb.models.Notification;
import com.bezkoder.spring.jwt.mongodb.models.Order;
import com.bezkoder.spring.jwt.mongodb.payload.request.SignupRequest;
import com.bezkoder.spring.jwt.mongodb.payload.request.SignupResponse;
import com.bezkoder.spring.jwt.mongodb.repository.OrderRepository;
import com.bezkoder.spring.jwt.mongodb.service.CartService;
import com.bezkoder.spring.jwt.mongodb.service.NotificationService;
import com.bezkoder.spring.jwt.mongodb.service.OrderService;
import com.bezkoder.spring.jwt.mongodb.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CartService cartService;

	@Autowired
	UserService userService;

	@Autowired
	NotificationService notificationService;

	@Autowired
	MongoOperations mongo;

	@Override
	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		if (order.getNumOrder() == 0) {
			order.setNumOrder(generateSequence("numOrder"));
		}
		return orderRepository.save(order);
	}

	@Override
	public OrderResponse addOrder(OrderRequest orderRequest) {
		// TODO Auto-generated method stub
		OrderResponse orderResponse = new OrderResponse();

		if (!orderRequest.getPassword().isEmpty() && orderRequest.getPassword() != null) {
			SignupResponse signupResponse = new SignupResponse();
			SignupRequest signUpRequest = new SignupRequest();
			signUpRequest.setFirstName(orderRequest.getOrder().getAddressEmetteur().getFirstName());
			signUpRequest.setLastName(orderRequest.getOrder().getAddressEmetteur().getLastName());
			signUpRequest.setAddress(orderRequest.getOrder().getAddressEmetteur().getAddress());
			signUpRequest.setUsername(orderRequest.getOrder().getAddressEmetteur().getEmail());
			signUpRequest.setPassword(orderRequest.getPassword());
			// signUpRequest.setIdCart(orderRequest.getIdCart());
			signUpRequest.setTelephone(orderRequest.getOrder().getAddressEmetteur().getTelephone());

			signupResponse = userService.registerUser(signUpRequest);

			if (signupResponse.isError()) {
				orderResponse.setError(true);
				orderResponse.setMessage(signupResponse.getMessage());

			} else {
				orderRequest.getOrder().setUserId(signupResponse.getUser().getId());

				Order newOrder = saveOrder(orderRequest.getOrder());

				if (newOrder != null) {
					// cartService.resetCart(orderRequest.getIdCart());
					// if(myCart.getUserId()== null || myCart.getUserId().length() == 0){
					// cartService.deleteCartById(orderRequest.getIdCart());
					// }
					//
					orderResponse.setError(false);
					orderResponse.setUser(signupResponse.getUser());
					orderResponse.setMessage("Compte cree et Votre commande a ete bien recu ");
					orderResponse.setOrder(newOrder);
					// String title,String message,String to,String userId,String type
					Notification notif = new Notification(
							"Nouvelle Commande " + newOrder.getNumOrder(),
							"Commande " + newOrder.getStatusOrder(),
							newOrder.getUserId(),
							"",
							newOrder.getId());
					notificationService.saveNotification(notif);
				}
			}

			return orderResponse;

		}

		Order newOrder = saveOrder(orderRequest.getOrder());

		if (newOrder != null) {

			// Cart myCart = cartService.getCartById(orderRequest.getIdCart());
			// if (myCart.getUserId() == null || myCart.getUserId().length() == 0) {
			// cartService.deleteCartById(orderRequest.getIdCart());
			// } else {
			// cartService.resetCart(orderRequest.getIdCart());
			// }
			orderResponse.setError(false);
			orderResponse.setUser(null);
			orderResponse.setMessage("Votre commande a ete bien recu ");
			orderResponse.setOrder(newOrder);
		}
		Notification notif = new Notification(
				"Nouvelle Commande "+ newOrder.getNumOrder(),
				"Commande " + newOrder.getStatusOrder(),
				newOrder.getUserId(),
				"",
				newOrder.getId());
		notificationService.saveNotification(notif);
		return orderResponse;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	public long generateSequence(String seqName) {
		// TODO Auto-generated method stub
		// DatabaseSequence counter =
		// mongo.findAndModify(query(where("_id").is(seqName)),
		// new Update().inc("seq",1),
		// options().returnNew(true).upsert(true),
		// DatabaseSequence.class);
		// return !Objects.isNull(counter) ? counter.getSeq() : 1;

		// get sequence id
		Query query = new Query(Criteria.where("_id").is(seqName));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("seq", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		DatabaseSequence seqId = mongo.findAndModify(query, update, options, DatabaseSequence.class);

		// return seqId.getSeq();
		return !Objects.isNull(seqId) ? seqId.getSeq() : 1;

	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		Order orderToUpdate = getOrderById(order.getId());
		orderToUpdate.setAddressEmetteur(order.getAddressEmetteur());
		orderToUpdate.setAddressExpdition(order.getAddressExpdition());
		orderToUpdate.setAmount(order.getAmount());
		orderToUpdate.setFraisExpedition(order.getFraisExpedition());
		orderToUpdate.setStatusPay(order.getStatusPay());
		orderToUpdate.setMdp(order.getMdp());
		if (order.getMdp().equals("Paiement a la livraison") && order.getStatusOrder() == 5) {
			orderToUpdate.setStatusPay("paye");
		}
		orderToUpdate.setProductsOrders(order.getProductsOrders());
		orderToUpdate.setStatusOrder(order.getStatusOrder());
		orderToUpdate.setTotal(order.getTotal());
		orderToUpdate.setUserId(order.getUserId());
		order = saveOrder(orderToUpdate);
		if (order != null) {
			Notification notif = notificationService.getNotificationByType(order.getId());
			notif.setRead(false);
			notif.setReaderUsers(null);
			notif.setDateNotif(LocalDateTime.now());
			notif.setTitle("Statut Commande N:" + order.getNumOrder());
			notif.setMessage("Commande " + order.getStatusOrder());
			// Notification notif = new Notification("Nouveau Statut Commande","Commande
			// "+order.getStatusOrder(),order.getUserId(),"");
			notificationService.saveNotification(notif);
			return order;
		}
		return null;
	}

	@Override
	public Order getOrderById(String id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id).get();
	}

	@Override
	public List<Order> getAllOrdersByUserId(String userId) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Direction.DESC, "orderDate");
		return orderRepository.findAllOrderByUserId(userId, sort);
	}

}
