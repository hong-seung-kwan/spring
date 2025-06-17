package com.example.demo.etc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTest {
	
	// 빈으로 등록된 인코더는 필요할때 꺼내 쓸 수 있다
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	void 인코더확인() {
		System.out.println("passwordEncoder: " + passwordEncoder);
	}
	
	@Test
	void 암호화테스트() {
		
		// 사용자의 패스워드
		String password = "1234";
		
		// 패스워드 암호화
		String enpw = passwordEncoder.encode(password);
		
		// 원본 대신 암호화된 패스워드를 member 테이블에 저장할 것
		
		System.out.println("암호화된 패스워드: " + enpw);
		
		// BCrypt는 단방향 인코더로 암호화된 패스워드를 다시 복호화할 수 없음
		// 로그인 어떻게 처리할까?
		// 로그인할 때 입력받은 패스워드도 암호화를 한 다음에
		// 테이블에 있는 패스워드와 같은지 비교하는 방식
		
		// 비교대상: 원본데이터, 암호화된 패스워드
		boolean result = passwordEncoder.matches("1234", enpw);
		
		System.out.println("패스워드가 맞는지? " + result);
	}
}
