package com.example.demo.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Product;

@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	ProductRepository repository;
	
	@Test
	void 상품추가() {
		
		Product product = new Product(0, "바지", 10000, "바지입니다", "ㅇㅇㅇㅇㅇ", "바지");
		
		repository.save(product);
		
	}
	
}
