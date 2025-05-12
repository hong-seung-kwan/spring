package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.ProductDTO;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	ProductService service;
	@Test
	void 상품추가() {
		ProductDTO dto = new ProductDTO(0, "아우터", 25000, "아우터입니다", "aaaa", "아우터");
		
		service.register(dto);
	}
	@Test
	void 상품조회() {
		ProductDTO result = service.read(1);
		System.out.println(result);
	}
}
