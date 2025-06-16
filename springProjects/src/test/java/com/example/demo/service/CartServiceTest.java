package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.CartDTO;

@SpringBootTest
public class CartServiceTest {

	@Autowired
	CartService service;
	@Test
	void 확인() {
		System.out.println(service);
	}
	@Test
	void 추가() {
		CartDTO dto = new CartDTO(0, "admin", 9, 1);
		
		service.register(dto);
	}
	@Test
	void 조회() {
		List<CartDTO> list = service.getListByUserId("admin");
		
		for(CartDTO dto : list) {
			System.out.println(dto);
		}
	}
}
