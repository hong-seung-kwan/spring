package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.MemberDTO;

@SpringBootTest
public class MemberServiceTest {
	
	@Autowired
	MemberService service;
	
	@Test
	void 서비스확인() {
		System.out.println("service: " + service);
	}

	@Test
	void 회원등록() {
		
		MemberDTO dto = MemberDTO.builder()
									.id("user1")
									.password("1234")
									.name("둘리")
									.build();
		
		service.register(dto);
	}
	
}
