package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int cartNo;
	
	@ManyToOne
	Member userId;
	
	@ManyToOne
	Product productNo;
	
	@Column
	int product_quantity;
	
}
