package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository repository;
	
	@Test
	void 리파지토리확인() {
		System.out.println("repository: " + repository);
	}
	@Test
	void 회원추가() {
		Member member = new Member("user1", "1234", "user1", "인천 연수구", "010-1111-2222", "admin");
		repository.save(member);
	}
	@Test
	void 회원삭제() {
		repository.deleteById("user1");
	}
}
