package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Order;

@SpringBootTest
public class Quiz7 {
	@Autowired
	OrderRepository repository;
	
	@Test
	public void 주소지인천() {
		List<Order> list = repository.get1("인천");
		for(Order order:list) {
			System.out.println(order);
		}
	}
	
	@Test
	public void 주문일7월3일() {
		LocalDate localDate = LocalDate.of(2023, 07, 03);
		List<Order> list = repository.get2(localDate);
		for(Order order : list) {
			System.out.println(order);
		}
	}
}
