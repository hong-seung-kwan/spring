package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.dto.MemberDTO;

// MemberService의 기능 확인하는 테스트 클래스

@SpringBootTest
public class MemberServiceTest {
	@Autowired
	MemberService service;
	
	@Test
	void 서비스확인() {
		System.out.println("service: " + service);
	}
	
	@Test
	void 회원목록조회() {
		
		// 첫번째 페이지 조회
		Page<MemberDTO> page = service.getList(0);
		
		// 회원 리스트만 꺼내기
		List<MemberDTO> list = page.getContent();
		
		for(MemberDTO dto : list) {
			System.out.println(dto);
		}
	}
	
	@Test
	void 회원가입() {
		
		// 사용중인 아이디는 회원가입 안됨
		
		// dto 생성
		MemberDTO dto = MemberDTO.builder()
									.id("admin")
									.name("둘리")
									.password("1234")
									.build();
		// 테이블에 새로운 회원을 추가
		service.register(dto);
	}
	
	@Test
	void 회원단건조회() {
		
		// user1의 정보 조회
		MemberDTO dto = service.read("user1");
		System.out.println(dto);
	}
}
