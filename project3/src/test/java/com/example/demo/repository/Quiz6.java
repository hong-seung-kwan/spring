package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Gift;

@SpringBootTest
public class Quiz6 {
	@Autowired
	GiftRepository repository;
	
	@Test
	public void 가격50000원이상() {
		List<Gift> list = repository.get1(50000);
		for(Gift gift : list ) {
			System.out.println(gift);
		}
	}
	
	@Test
	public void 상품명_세트로_끝나는() {
		List<Gift> list = repository.get2("세트");
		for(Gift gift : list) {
			System.out.println(gift);
		}
	}
	
	@Test
	public void 선물40000이하_품목생활용품() {
		List<Gift> list = repository.get3(40000,"생활용품");
		for(Gift gift : list) {
			System.out.println(gift);
		}
	}
}
