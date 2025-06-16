package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.CartDTO;
import com.example.demo.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	CartService service;
	
	@GetMapping("/cart")
	public void cart() {
		
	}
	@PostMapping("/cart")
	public void cartPost(CartDTO dto) {
		service.register(dto);
		
	}
}
