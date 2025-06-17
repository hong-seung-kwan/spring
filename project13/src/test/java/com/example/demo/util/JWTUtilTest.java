package com.example.demo.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.security.JWTUtil;

@SpringBootTest
public class JWTUtilTest {
	
	@Test
	void 토큰생성() throws Exception {
		
		// util 클래스 생성
		JWTUtil util = new JWTUtil();
		
		// 유틸클래스를 사용해서 토큰 하나 생성
		
		// 사용자 아이디 선언
		String id = "user";
		
		// 토큰을 발급할 때는 사용자의 아이디를 인자로 넣어야함
		// 유효성 체크시 사용함
		String token = util.generateToken(id);
		
		// 토큰 확인
		System.out.println("token: " + token);
	}
	
	@Test
	void 토큰유효성검사() throws Exception {
		
		// util 클래스 생성
		JWTUtil util = new JWTUtil();
		
		// 발급받은 토큰 선언
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3NTAxMzE3NjgsImV4cCI6MTc1MDEzMTgyOCwic3ViIjoidXNlciJ9.W5tdl-yxGd21NUjGJx4M_K1gOEtq8axTuX-fuwDroVI";
		
		// 토큰 유효성 검사
		// 토큰을 생성할때 넣었던 아이디가 나오는지 확인
		String id = util.validateAndExtract(token);
		
		System.out.println("아이디 추출: " + id);
	}
}
