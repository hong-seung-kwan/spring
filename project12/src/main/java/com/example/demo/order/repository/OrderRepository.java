package com.example.demo.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.order.entity.Order;
import java.time.LocalDate;


// repository: 데이터 추가, 조회, 수정, 삭제 SQL 생성하는 컴포넌트

// 리파지토리 만드는 방법
// 1. JPA 상속
// 2. entity와 pk 타입 작성
public interface OrderRepository extends JpaRepository<Order, Integer> {

	// 날짜를 기준으로 주문이력 조회
	@Query( value = "SELECT * FROM TBL_ORDER WHERE ORDER_DATE = :date", nativeQuery = true)
	List<Order> findByOrderDate(@Param("date") LocalDate date);
	
	// 날짜를 기준으로 주문이력 삭제
	// nativeQuery 속성이 없으면 jpql 문법으로 해석됨
	@Query(value = "DELETE FROM TBL_ORDER WHERE ORDER_DATE = :date",nativeQuery = true)
	void removeByOrderDate(@Param("date") LocalDate date);
	
}

/* 리파지토리에 기능을 추가하는 방법
 * 1. 쿼리 메소드
 * 2. 쿼리 어노테이션 - JPQL, 순수한 SQL
 * 규칙: 함수 이름은 자유롭게 작성할 것
 * 파라미터는 매개변수로 선언
 * */
 