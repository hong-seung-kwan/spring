package com.example.demo.lombok;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.lombok.Book.BookBuilder;

@SpringBootTest
public class BookTest {
	@Test
	// 클래스 이름을 같게하면 main java에 있는 Book 클래스로 인스턴스를 생성하는게 아닌
	// test java에 있는 Book 클래스의 인스턴스가 생성됨
	public void 도서클래스테스트() {
		Book book1 = new Book();
		book1.setBookName("책 제목1");
		book1.setPrice(10000);
		book1.setPublisher("책출판사1");
		book1.setPage(50);
		System.out.println(book1.getBookName());
		System.out.println(book1.getPrice());
		System.out.println(book1.getPublisher());
		System.out.println(book1.getPage());
		
		Book book2 = new Book("책 제목2", 15000, "책출판사2", 70);
		System.out.println(book2.toString());
		
		Book book3 =Book.builder()
			.bookName("책 제목3")
			.price(16000)
			.publisher("책출판사3")
			.page(100)
			.build();
		
		System.out.println(book3.toString());
		
		
	}
}


/*
 * 1. 모든 클래스는 메인 패키지 아래 추가할 것
 * 메인패키지 바깥에 클래스를 추가하면 동작 안됨.
 * (메인 클래스가 찾을 수 있는 위치에 있어야함)
 * 
 * 2. 테스트 클래스는 테스트할 클래스의 이름과 같으면 안됨
 * 폴더와 패키지경로가 달라도 같으면 안됨.
 * (예: 테스트할 클래스: Person / 테스트 클래스: Person)
 * 
 * 3. 단위테스트는 함수명을 한글로 작성해도 상관없음
 * 일반함수는 한글 x
 * 
 * 4. 단위테스트를 하나씩 실행할 때는 해당 함수에서 이름을 선택하고 실행
 * 일괄적으로 실행할때는 클래스 이름을 선택하고 실행
 */



