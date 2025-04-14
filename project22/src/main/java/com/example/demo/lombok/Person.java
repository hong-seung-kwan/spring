package com.example.demo.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 사람을 정의하는 클래스
// 각 필드의 getter 함수를 생성하는 기능
@Getter
@Setter
// object가 물려준 toString함수를 재정의하는 기능
// 함수의 원형은 인스턴스의 주소값을 반환, 재정의된 함수를 인스턴스의 데이터를 반환
@ToString
// 생성자
@AllArgsConstructor // 모든
@NoArgsConstructor // 디폴트
@Builder
public class Person {
	
	String name;
	int age;
}
