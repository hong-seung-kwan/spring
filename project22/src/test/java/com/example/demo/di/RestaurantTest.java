package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// 테스트 클래스 만들기
// 1. 스프링 환경 설정
// 스프링 컨테이너가 필요할때는 이 어노테이션을 써야함
@SpringBootTest
public class RestaurantTest {
	
	// 변수 선언만 있고 인스턴스를 생성한 부분은 없음
	// @Autowired가 붙어 있는 필드가 있으면
	// 컨테이너에서 타입이 일치하는 객체를 꺼내서 주입.
	@Autowired
	Restaurant restaurant;
	// 인스턴스 생성 코드가 없는데, 백종원이라는 객체가 저장되어 있음
	// 컨테이너 안에 동일한 타입의 빈이 2개 이상있으면, 에러남
	@Autowired
	Chef chef;
	
	// 2. 단위 테스트 만들기
	@Test
	public void 테스트() {
		
		// 테스트클래스의 쉐프와 레스토랑의 쉐프는 같음
		// 빈은 하나만 생성되어 필요한 필드에 공유됨
		
		System.out.println("Chef: " + chef);
		System.out.println("Restaurant의 Chef: " + restaurant.chef);
		
	}
}

/* 
 * 단위 테스트의 목적:
 * 특정 기능을 독립적으로 테스트 하기 위함.
 * 단위테스트에서 이것저것 테스트하면 안됨
 * 
 * 의존성 주입을 사용하는 이유? 
 * 클래스 안에서 필요한 객체를 직접 만들어서 사용하는 방식은
 * 유지보수에 한계가 있음
 * 
 * 의존성 주입 방식을 사용하면 해당 클래스를 직접 수정하지 않고
 * 객체를 교체할 수 있으므로 유지보수에 유리함
 * 
 * 레스토랑 클래스를 직접 수정하면 레스토랑을 사용하는 쪽에서도
 * 변화가 발생할 수 있기 때문에, 의존성 주입방식을 사용하는 것이
 * 유지보수에 좋다..
 * */
 