package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Members;

@SpringBootTest
public class MembersRepositoryTest {
	@Autowired
	MembersRepository repository;
	
	@Test
	public void 데이터추가() {
		Members members1 = Members.builder().userId("admin").grade("관리자").password("1234").build();
		Members members2 = Members.builder().userId("user1").grade("사용자").password("1234").build();
		Members members3 = Members.builder().userId("user2").grade("사용자").password("1234").build();
		Members members4 = Members.builder().userId("yoyt22").grade("관리자").password("1234").build();
		
		repository.save(members1);
		repository.save(members2);
		repository.save(members3);
		repository.save(members4);
	}
	
	@Test
	public void 데이터조회() {
		Optional<Members> optional = repository.findById("yoyt22");
		if(optional.isPresent()) {
			Members members = optional.get();
			System.out.println(members);
		}
	}
	@Test
	public void 데이터전체조회() {
		List<Members> list = repository.findAll();
		for(Members members : list) {
			System.out.println(members);
		}
	}
	
	@Test
	public void 데이터수정() {
		Optional<Members> optional = repository.findById("user2");
		if(optional.isPresent()) {
			Members members = optional.get();
			members.setPassword("5678");
			repository.save(members);
		}
	}
	
	@Test
	public void 데이터삭제() {
		repository.deleteById("user2");
	}
}
