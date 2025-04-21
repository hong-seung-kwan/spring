package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/board") // 중간경로
@Controller
public class MappingController2 {
	@ResponseBody
	@GetMapping("/list")
	public String list() {
		System.out.println("게시물 조회..");
		return "ok";
	}// 전체경로: /board/list
	@ResponseBody
	@PostMapping("/save")
	public String save() {
		System.out.println("게시물 등록..");
		return "ok";
	}// 전체경로: /board/save
	@ResponseBody
	@PutMapping("/modify")
	public String modify() {
		System.out.println("게시물 수정..");
		return "ok";
	}// 전체경로: /board/modify
	@ResponseBody
	@DeleteMapping("/remove")
	public String remove() {
		System.out.println("게시물 삭제..");
		return "ok";
	}// 전체경로: /board/remove
}
