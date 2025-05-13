package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public void productInfo(Model model,@RequestParam(name = "no") int productNo) {
		ProductDTO product = service.read(productNo);

		model.addAttribute("product", product);
	}
	
	@GetMapping("/modify")
	public void modify(Model model,@RequestParam(name = "no") int productNo) {
		ProductDTO product = service.read(productNo);

		model.addAttribute("product", product);
	}
	
	@PostMapping("/modify")
	public String modifyPost(ProductDTO dto) {
		service.modify(dto);
		
		return "redirect:/home";
	}

}
