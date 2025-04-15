package com.example.demo.optional;

import java.util.Optional;

import org.junit.jupiter.api.Test;

// Optional 클래스:
// 값을 감싸고 있는 클래스
// 객체 안에 값이 있는지 없는지 검사하는 함수를 제공
// 값이 있는지 미리 확인할 수 있기 때문에 변수를 잘못사용하는 에러를 방지할 수 있음

public class OptionalTest {	
	@Test
	public void 테스트() {
		
		String str = null;
		
		// 문자열을 담고 있는 Optional 객체 생성 
		// Optional 객체를 생성하는 방법: 생성자 대신 of 함수
		// of 함수는 빈값을 입력할 수 없음.
		Optional<String> opt = Optional.of(str);
		
		// Optional 객체 안에 있는 값 꺼내기
		String value = opt.get();
		System.out.println(value);
		
		// Optional 객체 안에 값이 비어있는지
		boolean isEmpty = opt.isEmpty();
		System.out.println(isEmpty);
		
		// Optional 객체 안에 값이 있는지
		boolean isPresent = opt.isPresent();
		System.out.println(isPresent);
	}
	@Test
	public void 테스트2() {
		
		// of 함수 대신 ofNullable함수를 사용하여 Optional 생성
		// of 함수: Optional을 생성하는 함수. 빈값 입력 불가
		// ofNullable: Optional을 생성하는 함수. 빈값 입력 가능
		Optional<String> opt = Optional.ofNullable(null);
		
//		boolean isEmpty = opt.isEmpty();
//		System.out.println(isEmpty);
		
		// 값을 바로 꺼내면 안되고
		// is~함수를 사용하여 값이 있는지 확인 후 꺼내기
		
		
//		boolean isPresent = opt.isPresent();
//		if(isPresent) {
//			String value = opt.get();
//			System.out.println("Optional 안에 있는 값: " + value);
//		}
		
		// optional 안에 값이 있으면 그대로 출력, 없으면 대체
		String str = opt.orElse("banana");
		System.out.println(str);
	}
	
	@Test
	public void 테스트3() {
		
		// optional 객체 생성
		Optional<String> opt = Optional.ofNullable("banana");
		
		// 조건문을 사용하여 값이 있는지 검사
		if(opt.isPresent()) {
			System.out.println("optional 안에 값이 있습니다..");
		}
		
		// Optional에서 제공하는 함수를 사용하여 값이 있는지 검사
		// 매개변수: optioanl 안에 value
		// Optional 안에 값이 있으면 함수가 실행됨
		opt.ifPresent((value) -> {
			System.out.println("optional 안에 값이 있습니다..");
			System.out.println("value: " + value);
		});
		
		// 함수형 인터페이스 구현 방법 => 람다식
	}
}
