package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Gift;

@SpringBootTest
public class GiftRepositoryTest {

	@Autowired
	GiftRepository repository;
	
	@Test
	public void 데이터추가() {
//		Gift gift1 = new Gift(0, "참치세트", 10000, "식품");
//		repository.save(gift1);
		List<Gift> list = new ArrayList<>();
		Gift gift2 = Gift.builder().no(0).name("햄세트").price(20000).type("식품").build();
		Gift gift3 = Gift.builder().no(0).name("샴푸세트").price(30000).type("생활용품").build();
		Gift gift4 = Gift.builder().no(0).name("세차용품").price(40000).type("생활용품").build();
		Gift gift5 = Gift.builder().no(0).name("주방용품").price(50000).type("생활용품").build();
		Gift gift6 = Gift.builder().no(0).name("노트북").price(60000).type("가전제품").build();
		Gift gift7 = Gift.builder().no(0).name("벽걸이TV").price(70000).type("가전제품").build();
		list.add(gift2);
		list.add(gift3);
		list.add(gift4);
		list.add(gift5);
		list.add(gift6);
		list.add(gift7);
		repository.saveAll(list);
	}
	
	@Test
	public void 데이터조회() {
		Optional<Gift> optional = repository.findById(2);
		if(optional.isPresent()) {
			Gift gift = optional.get();
			System.out.println(gift);
		}
	}
	
	@Test
	public void 데이터전체조회() {
		List<Gift> list = repository.findAll();
		System.out.println(list);
	}
	
	@Test
	public void 데이터수정() {
		Optional<Gift> optional = repository.findById(5);
		if(optional.isPresent()) {
			Gift gift = optional.get();
			gift.setName("수정한이름");
			repository.save(gift);
		}
	}
	
	@Test
	public void 데이터삭제() {
		repository.deleteById(2);
	}
	
	@Test
	public void 데이터전체삭제() {
		repository.deleteAll();
	}
}
