package com.example.demo.repository;

import java.util.ArrayList;
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
	public void 데이터추가() {
		List<Member> list = new ArrayList<>();
		Member member1 = Member.builder().user_id("admin").grade("관리자").password("1234").build();
		Member member2 = Member.builder().user_id("user1").grade("사용자").password("1234").build();
		Member member3 = Member.builder().user_id("user2").grade("사용자").password("1234").build();
		Member member4 = Member.builder().user_id("yoyt22").grade("관리자").password("1234").build();
		
		list.add(member1);
		list.add(member2);
		list.add(member3);
		list.add(member4);
		repository.saveAll(list);
	}
	
	@Test
	public void 데이터조회() {
		Optional<Member> optional = repository.findById("user2");
		if(optional.isPresent()) {
			Member member = optional.get();
			System.out.println(member);
		}
	}
	
	@Test
	public void 데이터전체조회() {
		List<Member> list = repository.findAll();
		System.out.println(list);
	}
	
	@Test
	public void 데이터수정() {
		Optional<Member> optional = repository.findById("user2");
		if(optional.isPresent()) {
			Member member = optional.get();
			member.setGrade("수정");
			repository.save(member);
		}
	}
	@Test
	public void 데이터삭제() {
		repository.deleteById("user2");
	}
	@Test
	public void 데이터전체삭제() {
		repository.deleteAll();
	}
}
