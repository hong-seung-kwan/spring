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
	void 회원가입테스트() {
		
		MemberDTO dto = MemberDTO.builder()
									.id("user2")
									.password("1234")
									.name("또치")
									.role("USER")
									.build();
		
		service.register(dto);
	}
	
}
