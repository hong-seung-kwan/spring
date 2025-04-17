package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MemberRepositoryTest {
	
	// jpa를 쓰지않은 순수한 클래스이므로 직접 생성해야함
	MemberRepository repository = new MemberRepository();
	
	@Test
	public void 리파지토리확인() {
		System.out.println(repository);
	}
	
	@Test
	public void 회원추가() {
		// 새로운 회원 생성
		// 회원번호 x 아이디와 패스워드만 입력
		Member member = Member.builder()
										.userId("user1")
										.password("1234")
										.build();
		Member member2 = Member.builder()
										.userId("user2")
										.password("1234")
										.build();
		repository.save(member);
		repository.save(member2);
		
		Member member1 = repository.findById(1);
		System.out.println("1번째 회원: " + member1);
		
		List<Member> list = repository.findAll();
		System.out.println("회원 목록: ");
		for(Member memberList : list) {
			System.out.println(memberList);
		}
		
		
		
		repository.clearStore();
		List<Member> clear = repository.findAll();
		System.out.println("삭제 후 회원 목록: ");
		for(Member memberList2 : clear) {
			System.out.println(memberList2);
		}
		
	}
}
