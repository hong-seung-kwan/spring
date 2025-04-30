package com.example.demo.order.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_stats")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stats {
	
	// pk를 정하는 기준: 중복되지 않는 데이터
	@Id
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	LocalDate orderDt; // 주문일
	
	long count; // 주문 건수
	
	int totalPrice; // 주문 총 금액
	
}
