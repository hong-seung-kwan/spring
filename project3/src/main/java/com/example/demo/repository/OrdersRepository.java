package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Orders;

// Repository란? 테이블에 데이터를 조회, 추가, 수정, 삭제하는 컴포넌트
// Repository 만드는 방법
// 1. JpaRepository 상속
// 2. entity, pk 타입 작성

// 인터페이스는 인스턴스를 생성할 수 없음
// 리파지토리가 만들어지는 과정
// 1. JpaRepository를 상속받는 인터페이스 생성 <수동>
// 2. OrderRepository를 상속받는 자식클래스(구현체) 생성 <자동>
// 3. 자식클래스로 인스턴스를 생성 <자동>
// 4. 컨테이너에 인스턴스(빈)가 등록됨 <자동>
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
