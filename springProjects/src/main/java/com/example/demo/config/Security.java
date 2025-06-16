package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// config 클래스는 일반 클래스와 달리,
// 스프링 프로젝트가 시작될때 제일 먼저 실행된다

// 시큐리티와 관련된 설정을 담고 있는 클래스
@Configuration
@EnableWebSecurity
public class Security {
	
	// 커스텀 필터체인을 만들어서 빈으로 등록
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
			.requestMatchers("/register").permitAll()
			.requestMatchers("/home").permitAll()
			.requestMatchers("/productRegister").permitAll()
			.requestMatchers("/productModify").permitAll()
			.requestMatchers("/login").permitAll()
			.requestMatchers("/cart").permitAll()
			.requestMatchers("/productInfo").permitAll();
		
		

		http.csrf( csrf -> csrf.disable());
				
		http.logout();
		
		
		http.formLogin( form -> {
			
			form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.permitAll() 

				.successHandler((request, response, authentication) -> {
					
					response.sendRedirect("/home");
				});
			
		});
		
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		// BCrypt: 암호화 인코더 종류
		return new BCryptPasswordEncoder();
		
	}
	
}
