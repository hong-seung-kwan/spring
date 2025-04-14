package com.example.demo.lombok;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// person 클래스의 기능을 테스트하는 클래스
// 테스트 클래스는 왜 test 폴더에 보관할까

// main java 폴더에는 테스트 클래스를 추가할 수 없음
// 테스트 클래스 만드는 방법

// 1. 테스트 환경 구성
@SpringBootTest
public class PersonTest {
	// 2. 단위 테스트 생성
	// 메인 클래스 없이 함수만 단독으로 실행 가능
	@Test
	public void 롬복테스트() {
		// 디폴트 생성자는 클래스 내부에 생성자가 하나도 없을때, 자동으로 생성됨
		// 사용자 정의 생성자가 하나라도 있으면 디폴트 생성자는 생성 안됨
		// 디폴트 생성자를 사용하여 인스턴스 생성
		Person person = new Person();	
		// setter를 사용해서 이름과 나이 입력
		person.setName("둘리");
		person.setAge(10);	
		// getter를 사용해서 이름과 나이 꺼내기
		String name = person.getName();
		int age = person.getAge();
		
		System.out.println(name + age);
		
		// 모든 값을 입력받는 생성자를 사용
		// 인스턴스 생성과 초기화를 동시에 진행
		// 단점: 값을 선택적으로 입력할 수 없음
		Person person2 = new Person("또치", 15);
		System.out.println(person2.getAge() + ',' + person.getAge());
		
		// builder를 사용하여 인스턴스를 생성
		// builder 객체 생성 -> 이름과 나이를 초기화 -> person의 인스턴스 생성
		// builder를 사용하여 필드를 선택해서 초기화할 수 있음.
		Person person3 = Person.builder()
			  .name("도우너")
			  .age(16)
			  .build();	
	}
}

// 스프링 프로젝트는 특정 기능을 독립적으로 실행할 수 없고,
// tomcat을 사용하여 프로젝트를 통째로 실행해야하기 때문에
// 부담스러움


/*
 * 단위 테스트 실행 방법.
 * 메소드 이름에 커서두기 > 우클릭 > run 메뉴 선택 > junit test 실행
 */