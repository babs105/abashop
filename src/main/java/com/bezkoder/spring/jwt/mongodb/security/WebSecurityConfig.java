package com.bezkoder.spring.jwt.mongodb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bezkoder.spring.jwt.mongodb.security.jwt.AuthEntryPointJwt;
import com.bezkoder.spring.jwt.mongodb.security.jwt.AuthTokenFilter;
import com.bezkoder.spring.jwt.mongodb.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/auth/**").permitAll()
				// .antMatchers("/product/getAllProduct","/product/getProductByCat/*,/product/getProductById/*").permitAll()
				.antMatchers(HttpMethod.GET, "/product/**").permitAll()
				.antMatchers("/product/create", "/product/updateProduct").hasRole("ADMIN")
				.antMatchers("/cart/**").permitAll()
				.antMatchers("/order/create").permitAll()
				.antMatchers("/order/getOrderById/*", "/order/updateOrder").hasRole("USER")
				.antMatchers("/order/getAllOrders").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/users/userDetail").hasRole("USER")
				.antMatchers("/users/updateUserProfile").hasRole("USER")
				.antMatchers("/users/updateUser", "/users/getAllUsers").hasRole("ADMIN")
				.antMatchers("/category/**").permitAll()
				.antMatchers("/category/create").hasRole("ADMIN")
				.antMatchers("/notif/getUserNotification/*","/notif/updateAllNotification","/notif/updateNotification").hasRole("USER")
				.antMatchers("/notif/getAllNotification").hasRole("ADMIN")
				.antMatchers("/notif/**").permitAll()
				.antMatchers("/push/**").permitAll()
				.antMatchers("/tarifZone/**").permitAll()
				// .antMatchers("/tarifZone/getTarifZoneById").hasRole("ADMIN")
				.antMatchers("/tarifZone/create").hasRole("ADMIN")
				.anyRequest().authenticated();
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
