package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.ProductService;



@Controller
public class HomeController {
	
	@Autowired
	ProductService service;

	@GetMapping("/home")
	public void home() {

	}
}
