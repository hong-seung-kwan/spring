package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Book;

@SpringBootTest
public class Quiz5 {
	@Autowired
	BookRepository repository;
	
	@Test
	public void 자바프로그래밍입문책검색() {
		List<Book> list = repository.get1("자바프로그래밍입문");
		for(Book book:list) {
			System.out.println(book);
		}
	}
	
	@Test
	public void 가격이_3만원이상_출판사_남가람북스검색() {
		List<Book> list = repository.get2(30000, "남가락북스");
		for(Book book : list) {
			System.out.println(book);
		}
	}
	
	@Test
	public void 출판사가_한빛_또는_이지스_검색() {
		List<Book> list = repository.get3("한빛출판사", "이지스퍼블리싱");
		for(Book book : list) {
			System.out.println(book);
		}
	}
}
