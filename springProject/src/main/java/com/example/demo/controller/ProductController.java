package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService service;

	@GetMapping("/productReg")
	public void register() {

	}

	@PostMapping("/productReg")
	public void registerPost(ProductDTO dto) {
		service.register(dto);
	}

//	@GetMapping("/productInfo")
//	public void info() {
//
//	}

	@GetMapping("/productInfo")
	public void info(Model model) {
		List<ProductDTO> product = service.getList();

		model.addAttribute("product", product);
	}

}
