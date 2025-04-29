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
public class SecurityConfig {
	
	// 커스텀 필터체인을 만들어서 빈으로 등록
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// 메뉴 접근 권한 설정
		// requestMatchers: URL 경로를 사용하여 접근 권한을 설정하는 함수
		
		// 인자: url 주소
		// requestMatchers: url주소(메뉴) 설정
		// permitAll, authenticated, hasAnyRole: 접근 권한
		
		// permitAll: 아무나 접근 가능
		// authenticated: 인증된 사람만 접근 가능 (로그인한 사람)
		// hasAnyRole: 권한을 가지고 있는 사람만 접근 가능
		
		// 게시물관리와 댓글관리는 일반사용자 또는 관리자만 접근 가능
		// 회원관리는 관리자만 접근 가능
		http.authorizeRequests()
			.requestMatchers("/register").permitAll()
			.requestMatchers("/").authenticated()
			.requestMatchers("/board/*").hasAnyRole("ADMIN", "USER")
			.requestMatchers("/comment/*").hasAnyRole("ADMIN", "USER")
			.requestMatchers("/member/*").hasAnyRole("ADMIN");
		
		// CSFR 비활성화
		// 시큐리티는 기본적으로 CSRF 기능이 활성화되어있기 때문에
		// POST 메소드를 사용할 수 없음
		// POST 메소드를 사용하기 위해서 CSRF	기능을 비활성화시켜야함
		http.csrf( csrf -> csrf.disable());
		
		// 로그인 및 로그아웃 기능 추가
//		http.formLogin();
		
		http.logout();
		
		// 시큐리티가 제공하는 기본 페이지 대신, 커스텀 로그인 페이지를 적용
		http.formLogin( form -> {
			// 커스텀 로그인 페이지 URL 설정
			form
				.loginPage("/customlogin")
				.loginProcessingUrl("/login")
				.permitAll() // 접근권한 - 아무나 사용 가능
				// 로그인 성공 후 작업 처리
				// 매개변수: 
				// request - http request 객체
				// response - http response 객체
				.successHandler((request, response, authentication) -> {
					// 로그인에 성공하면 무조건 메인페이지로 이동
					response.sendRedirect("/");
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
