package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 엔티티란? 테이블 구조를 정의하는 클래스
// 리파지토리는 엔티티를 사용하여 sql을 생성함
// 엔티티 만드는 방법:
// 1. @Entity, @Table 필수. lombok은 옵션
// 2. 실제 테이블 구조에 맞게 필드 작성하기
@Entity
@Table(name = "tbl_orders") // 실제 테이블 명
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {

	// entity는 반드시 하나 이상의 pk를 가져야 함

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	int orderNo;

	// 컬럼의 크기와 notnull여부
	@Column(length = 30, nullable = false)
	String customerName;
	
	// 날짜를 입력할때 문자열의 패턴
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	LocalDate orderDate;
	
	@Column(length = 100)
	String shipAddress;
}

// 실제 데이터베이스에 테이블이 있으면 그대로 사용
// 테이블이 없고 옵션이 설정되어 있으면 새로운 테이블 생성됨
// 자바 class와 db의 table이 연결될 때,
// 변수명과 컬럼명은 자동으로 매핑됨
// 그래서 굳이 변수명을 스네이크 기법으로 작성할 필요가 없음