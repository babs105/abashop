package com.bezkoder.spring.jwt.mongodb.controllers;

import java.util.List;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jwt.mongodb.models.Cart;
import com.bezkoder.spring.jwt.mongodb.payload.request.LoginRequest;
import com.bezkoder.spring.jwt.mongodb.payload.request.SignupRequest;
import com.bezkoder.spring.jwt.mongodb.payload.request.SignupResponse;
import com.bezkoder.spring.jwt.mongodb.payload.response.JwtResponse;
import com.bezkoder.spring.jwt.mongodb.repository.RoleRepository;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;
import com.bezkoder.spring.jwt.mongodb.security.jwt.JwtUtils;
import com.bezkoder.spring.jwt.mongodb.security.services.UserDetailsImpl;
import com.bezkoder.spring.jwt.mongodb.service.CartService;
import com.bezkoder.spring.jwt.mongodb.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	CartService cartService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		logger.info("IdUSER " + userDetails.getId());

		// cart visitor
		// if(loginRequest.getIdCart() != null) {
		//
		// Cart myCart = cartService.getCartByUserId(userDetails.getId());
		// Cart visitorCart = cartService.getCartById(loginRequest.getIdCart());
		//// logger.info("cart :"+myCart.getId());
		//
		// if(myCart.getProducts().isEmpty()) {
		// myCart.setProducts(visitorCart.getProducts());
		// myCart.setTotal(visitorCart.getTotal());
		// }else {
		// boolean isPresent=false;
		// for(int i=0 ;i<visitorCart.getProducts().size();i++) {
		// for(int j=0;j<myCart.getProducts().size();j++) {
		// if(visitorCart.getProducts().get(i).getId().equals(myCart.getProducts().get(j).getId())){
		// isPresent = true ;
		// break;
		// }
		// }
		// if(isPresent == false) {
		//
		// myCart.getProducts().add(visitorCart.getProducts().get(i));
		// myCart.setTotal(myCart.getTotal() +
		// myCart.getProducts().get(i).getCaract().get(0).getPrice() *
		// myCart.getProducts().get(i).getCaract().get(0).getQuantity());
		// }
		//
		// }
		// }
		// myCart.setQuantity(myCart.getProducts().size());
		// cartService.createCart(myCart);
		//
		// ///////////////// ################ delete cartvisitor ##################
		// //////////////
		// cartService.deleteCartById(loginRequest.getIdCart());
		// }
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getFirstName(),
				userDetails.getLastName(),
				userDetails.getAddress(),
				userDetails.getTelephone(),
				roles));
	}

	@PostMapping("/signup")
	public SignupResponse registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return userService.registerUser(signUpRequest);
	}
}
