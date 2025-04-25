package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {
	
	@Autowired
	MemberRepository repository;
		
	@Test
	void 회원추가() {
		// 엔티티 생성
		Member member = Member.builder()
									.id("user1")
									.name("둘리")
									.password("1234")
									.build();
		// 테이블에 새로운 회원 추가
		repository.save(member);
	}
	
	@Test
	void 회원추가2() {
		Member member = Member.builder()
									.id("user2")
									.name("또치")
									.password("1234")
									.build();
		
		repository.save(member);
	}
	
	@Test
	void 회원삭제() {		
		// user1을 삭제하는 방법
		// 1. user1이 작성한 모든 게시물을 삭제
		// 2. user1을 삭제
		repository.deleteById("user1");
	}
	
	@Test
	void 테스트데이터추가() {
		
		for(int i = 1; i<=30; i++) {
			// member 엔티티 생성
			Member member = Member.builder()
									.id("user"+i)
									.name("둘리")
									.password("1234")
									.build();
			// 테이블에 새로운 회원 추가
			repository.save(member);
		}
		
	}
	
}

