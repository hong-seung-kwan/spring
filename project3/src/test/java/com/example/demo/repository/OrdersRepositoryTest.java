package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Orders;

@SpringBootTest
public class OrdersRepositoryTest {

	// 컨테이너에 있는 빈을 꺼내서 필드에 주입
	@Autowired
	OrdersRepository repository;

	@Test
	public void 리파지토리확인() {
		System.out.println(repository);

		// OrdersRepository는 JpaRepository로부터 save,find,delete 함수를 물려받음
		// OrdersRepository는 함수를 사용하여 order 테이블에 있는 데이터를 조작할 수 있음
		repository.save(null);
		repository.findAll();
		repository.deleteAll();
	}

	@Test
	public void 데이터추가() {

		// 데이터 중에서 주문번호는 자동으로 생성됨
		// 0을 입력하면 빈값으로 처리
		// 데이터 중에서 고객명, 배송지, 주문일은 직접 입력

		LocalDate date1 = LocalDate.of(2023, 7, 1);
		LocalDate date2 = LocalDate.of(2023, 7, 2);
		LocalDate date3 = LocalDate.of(2023, 7, 3);

		Orders orders1 = new Orders(0, "둘리", date1, "인천 구월동");

		// no를 생략
		Orders orders2 = Orders.builder().customerName("또치").orderDate(date2).shipAddress("인천 연수동").build();

		Orders orders3 = Orders.builder().customerName("도우너").orderDate(date3).shipAddress("부산 동래동").build();
		Orders orders4 = Orders.builder().customerName("마이콜").orderDate(date1).build();
		Orders orders5 = Orders.builder().customerName("고길동").orderDate(date2).build();

		repository.save(orders1);
		repository.save(orders2);
		repository.save(orders3);
		repository.save(orders4);
		repository.save(orders5);
	}

	@Test
	public void 데이터단건조회() {

		// orders 테이블에서 no가 1번인 데이터 조회
		// find: 조회
		// by: 조건
		// id: primary key
		Optional<Orders> optional = repository.findById(10);

		if (optional.isPresent()) {
			Orders orders = optional.get();
			System.out.println(orders);
		}
	}
	
	@Test
	public void 데이터전체조회() {
		// orders 테이블 안에 있는 모든 데이터를 조회
		List<Orders> list = repository.findAll();
		System.out.println(list);
	}
	
	@Test
	public void 데이터수정() {
		
		// 1. 1번 데이터 조회
		Optional<Orders> optional = repository.findById(1);
		
		// 2. 데이터가 존재하는지 확인
		if(optional.isPresent()) {
			// 수정이 필요한 부분만 일부 변경
			Orders orders = optional.get();
			orders.setShipAddress("서울 신림동");
			// 변경된 데이터를 테이블에 업데이트
			// save 함수 호출시, no가1인 데이터가 있으면 update
			// 없으면 insert
			repository.save(orders);
		}
	}
	
	@Test
	public void 데이터단건삭제() {
		// 1번 데이터 삭제
		// delete: 데이터 삭제
		// by: 조건
		// id: primary key
		repository.deleteById(1);
	}
	
	@Test
	public void 일괄삭제() {
		repository.deleteAll();
	}
}
