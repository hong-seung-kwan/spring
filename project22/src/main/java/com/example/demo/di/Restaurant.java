package com.example.demo.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 클래스에 @Component라는 어노테이션이 붙어있으면
// 스프링 프로젝트가 시작될때, 스프링 컨테이너에 객체가 저장된다
@Component
public class Restaurant {
	
	// 쉐프 (백종원 또는 이연복)
	// 1.필요한 객체를 직접 만들어서 사용하는 방식
//	Chef chef = new 백종원();
//	Chef chef = new 이연복();
	
	// 2.의존성주입. 외부에 필요한 객체를 넣어주는 방식
	
	// 필드에 @Autowired가 붙어있으면
	// 컨테이너에 있는 객체를 꺼내서 주입한다
	@Autowired
	public Chef chef;
	
	// 사용하는 객체가 달라지면 코드를 수정해야함
	public Chef chef2 = new 이연복();
	
}

/* 
 * 레스토랑에는 무조건 한명의 쉐프가 있어야 한다. 
 * 그리고 쉐프는 "백종원" 또는 "이연복"이 될 수 있다.
 * 쉐프를 교체하기 위한 코드를 작성
 * */
