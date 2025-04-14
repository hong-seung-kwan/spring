package com.example.demo.lombok;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarTest {
	@Test
	public void 단위테스트1(){
		System.out.println("aa");
//		Car car1 = new Car();
//		car1.setName("자동차1");
//		car1.setCompany("제조사1");
//		car1.setColor("검정색");
//		
//		System.out.println(car1.getName()+" "+car1.getCompany()+" "+car1.getColor());
//		
//		Car car2 = new Car("자동차2", "제조사2", "빨강");
//		System.out.println(car2);
//		
//		Car car3 = Car.builder()
//					  .name("자동차3")
//					  .company("제조사2")
//					  .color("파랑")
//					  .build();
//		System.out.println(car3);
//		
	}
	@Test
	public void 단위테스트2() {
		System.out.println("bb");
	}
}
