package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.Stats;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.order.repository.StatsRepository;

@SpringBootTest
public class StatsRepositoryTest {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	StatsRepository statsRepository;
	
	@Test
	void 리파지토리확인() {
		System.out.println("orderRepository: " + orderRepository);
		System.out.println("statsRepository: " + statsRepository);
	}
	
	@Test
	void 통계데이터추가() {
		LocalDate date = LocalDate.now();	
		List<Order> list = orderRepository.findByOrderDate(date);
		int totalPrice = 0;
		
		for(Order order :list) {
//			System.out.println(order);
			totalPrice = totalPrice + order.getPrice();
		}		
		long count = list.size();
			
		Stats stats = new Stats(date, count, totalPrice);		
		statsRepository.save(stats);
			
	}
	
	@Test
	void 주문건수와총금액_구하기() {
		
		// 오늘 들어온 주문이력 조회
		// 오늘 날짜 생성
		LocalDate now = LocalDate.now();
		LocalDate date = now.minusDays(1);
		
		// 테이블에서 데이터 조회
		List<Order> list = orderRepository.findByOrderDate(date);
		
		// 결과 확인
		for(Order order : list) {
			System.out.println(order);
		}
		
		// 2. 주문건수와 총금액 구하기
		
		// 주문건수
		// stream이 가지고 있는 함수를 사용하여 개수 구하기
		long count = list.stream().count();
		
		// 총금액
		// stream의 map 함수를 사용해서
		// order -> order.price로 변환
		
		// 기본형 스트림에는 sum 함수가 없음
		// sum은 정수형 스트림에만 있음
		
		// 상품가격 모두 더하기
		int totalPrice = list.stream()
							 .mapToInt(order -> order.getPrice())
							 .sum();
		// 3. 통계 데이터 생성
		Stats stats = Stats.builder()
							.orderDt(date)
							.count(count)
							.totalPrice(totalPrice)
							.build();
		
		// 4. 통계 테이블에 새로운 데이터 추가
		statsRepository.save(stats);
	}
	
	@Test
	void 어제들어온_주문이력_삭제() {
		
		// order 테이블에서 어제 들어온 이력을 모두 삭제
		LocalDate now = LocalDate.now();
		LocalDate date = now.minusDays(1);
		
		orderRepository.removeByOrderDate(date);		
	}
	
}





