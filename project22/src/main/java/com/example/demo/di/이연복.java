package com.example.demo.di;

import org.springframework.stereotype.Component;

// @Component의 위치만 바꿔주면 쉐프가 교체
// 이 클래스의 타입: "이연복", "Chef"
@Component
public class 이연복 implements Chef {

}
/* 
 * 프로젝트가 시작이 될때, 스프링 컨테이너는 모든 클래스를 스캔하여 
 * @Component가 붙어 있는 클래스를 찾는다.
 * 그리고 해당 클래스의 인스턴스를 만들어서 컨테이너에 넣는다.
 * 이렇게 저장된 인스턴스를 빈이라고 부른다.
 * 
 * 이렇게 프로젝트에 필요한 객체를 미리 생성해놓고
 * 필요한 순간에 꺼내서 사용한다
 * 
 * 빈은 싱글톤으로 관리된다
 * 딱 하나의 인스턴스만 만들고 더이상 생성하지 않기 때문에
 * 메모리 효율이 높아진다
 * */
 