package com.example.demo.di;

import org.springframework.stereotype.Component;

@Component
public class MathTeacher implements Teacher {
	public void teach() {
		System.out.println("수학 선생님이 수업을 가르칩니다");
	}
}
