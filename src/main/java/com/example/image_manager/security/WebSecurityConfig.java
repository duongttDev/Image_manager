package com.example.image_manager.security;

import com.example.image_manager.security.jwt.JwtAuthenticationEntryPoint;
import com.example.image_manager.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

	private UserDetailsService userDetailsService;

	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	private JwtAuthenticationFilter authenticationFilter;

	public WebSecurityConfig(UserDetailsService userDetailsService,
			JwtAuthenticationEntryPoint authenticationEntryPoint, JwtAuthenticationFilter authenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
					CorsConfiguration config = new CorsConfiguration();
					config.setAllowedOriginPatterns(Collections.singletonList("*"));
					config.setAllowedMethods(Collections.singletonList("*"));
					config.setAllowCredentials(true);
					config.setAllowedHeaders(Collections.singletonList("*"));
					config.setExposedHeaders(Arrays.asList("Authorization"));
					config.setMaxAge(3600L);
					return config;
				})).csrf((csrf) -> csrf.disable())
				.addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests().requestMatchers("/api/user/**")
				.permitAll().requestMatchers("/api/auth/**")
				.permitAll().requestMatchers("/api/cart_item/**")
				.permitAll().requestMatchers("/api/cart/**")
				.permitAll().requestMatchers("/api/search/**")
				.permitAll().requestMatchers("/api/publisher/**")
				.permitAll().requestMatchers("/api/author/**")
				.permitAll().requestMatchers("/api/department/**")
				.permitAll().requestMatchers("/api/book/get-one",
						"/api/book/search", "/api/book/favorite", "/api/book/add-favorite", "api/book", "/api/order/")
				.permitAll().anyRequest().authenticated();
//				.authorizeHttpRequests((requests) -> requests.requestMatchers("/api/user/**").permitAll()
//						.requestMatchers("/api/auth/**").permitAll().requestMatchers("/api/cart_item/**").permitAll()
//						.requestMatchers("/api/cart/**").permitAll()
//						.requestMatchers("/api/book/get-one", "/api/book/search", "/api/book/favorite",
//								"/api/book/add-favorite", "/api/book")
//						.permitAll().anyRequest().authenticated())
//				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return http.build();


	}

}
