package com.example.demo.order.entity;

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

// entity: 테이블 구조 정의 클래스

@Entity
@Table(name = "tbl_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Order {
	
	// no 컬럼을 primary key로 설정
	// auto increment 옵션 추가
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int no;
	
	@Column(length = 50)
	String productName;
	
	int price;
	
	LocalDate orderDate;
	
}
