package com.example.demo.lombok;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentTest {
	@Test
	public void 학생테스트() {
		Student student1 = new Student();
		student1.setId(1111);
		student1.setName("학생1");
		student1.setAge(20);
		
		System.out.println(student1.getId());
		
		Student student2 = new Student(1112, "학생2", 21);
		System.out.println(student2);
		
		Student student3 = Student.builder()
						          .id(1113)
						          .name("학생3")
						          .age(22)
						          .build();
		System.out.println(student3);
	}
}
