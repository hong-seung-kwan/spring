package com.example.demo.repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Book;



@SpringBootTest
public class BookRepositoryTest {

	@Autowired
	BookRepository repository;
	
	@Test
	public void 확인() {
		System.out.println(repository);
	}
	
	@Test
	public void 데이터추가() {
		Book book = new Book(0, 20000, "한빛출판사", "자바프로그래밍입문");
		repository.save(book);
		
		List<Book> list = new ArrayList<>();
		Book book2 = Book.builder()
						 .book_no(0)
						 .price(25000)
						 .publisher("남가락북스")
						 .title("스프링부트프로젝트")
						 .build();
		Book book3 = Book.builder().book_no(0).price(40000).publisher("남가람북스").title("실무로 끝내는 PHP").build();
		Book book4 = Book.builder().book_no(0).price(35000).publisher("이지스퍼블리싱").title("알고리즘코딩테스트").build();
		
		list.add(book2);
		list.add(book3);
		list.add(book4);
		repository.saveAll(list);		
	}
	
	@Test
	public void 데이터단건조회() {
		Optional<Book> optional = repository.findById(3);
		if(optional.isPresent()) {
			Book book = optional.get();
			System.out.println(book);
		}
	}
	
	@Test
	public void 데이터전체조회() {
		List<Book> list = repository.findAll();
		System.out.println(list);		
	}
	
	@Test
	public void 데이터수정() {
		Optional<Book> optional = repository.findById(1);
		if(optional.isPresent()) {
			Book book = optional.get();
			book.setPrice(15000);
			repository.save(book);
		}
	}
	
	@Test
	public void 데이터삭제() {
		repository.deleteById(1);
	}
	
	@Test
	public void 데이터전체삭제() {
		repository.deleteAll();
	}
}
