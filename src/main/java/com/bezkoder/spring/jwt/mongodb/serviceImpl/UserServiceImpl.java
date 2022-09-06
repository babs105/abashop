package com.bezkoder.spring.jwt.mongodb.serviceImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jwt.mongodb.dto.ProfileRequest;
import com.bezkoder.spring.jwt.mongodb.dto.ProfileResponse;
import com.bezkoder.spring.jwt.mongodb.models.Cart;
import com.bezkoder.spring.jwt.mongodb.models.ERole;
import com.bezkoder.spring.jwt.mongodb.models.Role;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.payload.request.SignupRequest;
import com.bezkoder.spring.jwt.mongodb.payload.request.SignupResponse;
import com.bezkoder.spring.jwt.mongodb.payload.response.MessageResponse;
import com.bezkoder.spring.jwt.mongodb.repository.RoleRepository;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;
import com.bezkoder.spring.jwt.mongodb.security.services.UserDetailsImpl;
import com.bezkoder.spring.jwt.mongodb.service.CartService;
import com.bezkoder.spring.jwt.mongodb.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    CartService cartService;
    
    @Autowired
	PasswordEncoder encoder;
	
	@Override
	public User getLoggedUserDetails() {
		// TODO Auto-generated method stub
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = getUserByUsername(userDetails.getUsername());
		     
		
		
		return user;
	}

	@Override
	public User editUserDetail(SignupRequest signUpRequest) {
		// TODO Auto-generated method stub
	
		return updateUser(signUpRequest);
	}

	@Override
	public User updateUser(SignupRequest signUpRequest) {
		// TODO Auto-generated method stub
		User userToUpdate = getUserByUsername(signUpRequest.getUsername());
//		userToUpdate.setEmail(signUpRequest.getEmail());
		
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "ROLE_MODERATOR":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		if(signUpRequest.getPassword() != null) {
			userToUpdate.setPassword(encoder.encode(signUpRequest.getPassword()));
		}
		userToUpdate.setRoles(roles);
		userToUpdate.setTelephone(signUpRequest.getTelephone());
		userToUpdate.setFirstName(signUpRequest.getFirstName());
		userToUpdate.setLastName(signUpRequest.getLastName());
		userToUpdate.setAddress(signUpRequest.getAddress());

		return  userRepository.save(userToUpdate);
	}

	@Override
	public User getUserByUsername(String username) {
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return user;
	}

	@Override
	public SignupResponse registerUser(SignupRequest signUpRequest) {
		
		SignupResponse signupResponse = new SignupResponse();
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
		   signupResponse.setUser(null);
		   signupResponse.setError(true);
		   signupResponse.setMessage("Erreur: Email est deja utilise!");
		} else {

			// Create new user's account
			User user = new User(signUpRequest.getUsername(),
					  encoder.encode(signUpRequest.getPassword()),
					  signUpRequest.getFirstName(),
					  signUpRequest.getLastName(),
					  signUpRequest.getTelephone(),
					  signUpRequest.getAddress());

			
//			User user = new User(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()));

			Set<String> strRoles = signUpRequest.getRoles();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Erreur: Role non trouve."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "ROLE_ADMIN":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Erreur: Role non trouve."));
						roles.add(adminRole);

						break;
					case "ROLE_MODERATOR":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Erreur: Role non trouve."));
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Erreur: Role non trouve."));
						roles.add(userRole);
					}
				});
			}
			user.setRoles(roles);
			user = userRepository.save(user);
//			if(user != null) {
//				if(signUpRequest.getIdCart() == null) {
//					Cart newCart = new Cart();
//					newCart.setUserId(user.getId());
//					cartService.createCart(newCart);	
//				}
/////////////////// ################ delete cartvisitor ################## //////////////
////				
////				 cartService.deleteCartById(signUpRequest.getIdCart());
//			else {
//					Cart myCart = cartService.getCartById(signUpRequest.getIdCart());
//					myCart.setUserId(user.getId());
//					cartService.createCart(myCart);
//				}	
//			}
			signupResponse.setUser(user);
			signupResponse.setError(false);
			signupResponse.setMessage("Compte cree avec success et Connectez-vous maintenants");
		}

		return signupResponse;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public ProfileResponse updateUserProfile(ProfileRequest profileRequest) {
		// TODO Auto-generated method stu\
		User userToUpdate = getUserByUsername(profileRequest.getUsername());
				userToUpdate.setFirstName(profileRequest.getFirstName());
				userToUpdate.setLastName(profileRequest.getLastName());
				userToUpdate.setAddress(profileRequest.getAddress());
				userToUpdate.setTelephone(profileRequest.getTelephone());
				userToUpdate.setId(profileRequest.getId());
				
				if(profileRequest.getPassword() != null) {
					userToUpdate.setPassword(encoder.encode(profileRequest.getPassword()));
				}
			
				
				List<String> strRoles = profileRequest.getRoles();
				Set<Role> roles = new HashSet<>();

				if (strRoles == null) {
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Erreur: Role non trouve."));
					roles.add(userRole);
				} else {
					strRoles.forEach(role -> {
						switch (role) {
						case "ROLE_ADMIN":
							Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
									.orElseThrow(() -> new RuntimeException("Erreur: Role non trouve."));
							roles.add(adminRole);

							break;
						case "ROLE_MODERATOR":
							Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
									.orElseThrow(() -> new RuntimeException("Erreur: Role non trouve."));
							roles.add(modRole);

							break;
						default:
							Role userRole = roleRepository.findByName(ERole.ROLE_USER)
									.orElseThrow(() -> new RuntimeException("Erreur: Role non trouve."));
							roles.add(userRole);
						}
					});
				}
				userToUpdate.setRoles(roles);
				userToUpdate = userRepository.save(userToUpdate);
				
				if(userToUpdate != null) {
					ProfileResponse profileResponse	= new ProfileResponse();
					profileResponse.setUsername(userToUpdate.getUsername());
					profileResponse.setFirstName(userToUpdate.getFirstName());
					profileResponse.setLastName(userToUpdate.getLastName());
					profileResponse.setAddress(userToUpdate.getAddress());
					profileResponse.setTelephone(userToUpdate.getTelephone());
					profileResponse.setRoles(profileRequest.getRoles());
					profileResponse.setAccessToken(profileRequest.getAccessToken());
					profileResponse.setId(userToUpdate.getId());
					profileResponse.setType(profileRequest.getType());
					return profileResponse;
				} else {
					return null;
					
				}
				
				
//		User updateUser = updateUser(signUpRequest)
		
	}

	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

}
