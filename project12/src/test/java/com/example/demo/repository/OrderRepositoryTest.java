package com.example.demo.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.order.entity.Order;
import com.example.demo.order.repository.OrderRepository;

@SpringBootTest
public class OrderRepositoryTest {

	@Autowired
	OrderRepository repository;
	
	@Test
	void 리파지토리확인() {
		System.out.println("repository : " + repository);
	}
	
	@Test
	void 데이터추가() {
		List<Order> list = new ArrayList<>();
		
		Order order1 = new Order(0, "무선 이어폰", 10000, LocalDate.of(2025, 4, 29));
		Order order2 = new Order(0, "블루투스 스피커", 20000, LocalDate.of(2025, 4, 29));
		Order order3 = new Order(0, "스마트 워치", 30000, LocalDate.of(2025, 4, 30));
		Order order4 = new Order(0, "노트북", 40000, LocalDate.of(2025, 4, 30));
		Order order5 = new Order(0, "게이밍 마우스", 50000, LocalDate.of(2025, 4, 30));
		
		list.add(order1);
		list.add(order2);
		list.add(order3);
		list.add(order4);
		list.add(order5);
		
		repository.saveAll(list);
	}
	
	@Test
	void 주문일기준으로_주문이력_조회() {
		
		// 함수에 넣을 매개변수를 먼저 생성
		// 오늘 날짜를 가져오는 방법
//		LocalDate date = LocalDate.of(2025, 4, 30);
//		LocalDate date = LocalDate.now();
//		System.out.println("오늘날짜 : " + date);
		
		// 어제 날짜를 가져오는 방법
		LocalDate now = LocalDate.now();
		LocalDate date = now.minusDays(1);
		System.out.println("어제날짜 : " + date);
		
		List<Order> list = repository.findByOrderDate(date);
		
		for(Order order : list) {
			System.out.println(order);
		}				
	}

	@Test
	void 주문일기준으로_주문이력_삭제() {
		
		// 오늘 날짜 생성
		LocalDate now = LocalDate.now();
		
		// 어제 날짜 생성
		// 오늘 - 1일
		LocalDate date = now.minusDays(1);
		
		// 오늘 들어온 주문이력을 삭제
		repository.removeByOrderDate(date);		
	}
}
