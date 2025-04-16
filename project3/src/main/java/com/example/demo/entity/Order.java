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

@Entity
@Table(name = "tbl_order")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int order_no;
	// int orderNo;
	
	@Column(length = 30,nullable = false)
	String customer_name;
	
	@DateTimeFormat(pattern = "YYYY-MM-dd")
	@Column(nullable = false)
	LocalDate order_date;
	
	@Column(length = 100)
	String ship_address;
}
