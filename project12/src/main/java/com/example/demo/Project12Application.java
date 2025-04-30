package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// 스케줄러 기능 활성화
@EnableScheduling
public class Project12Application {

	public static void main(String[] args) {
		SpringApplication.run(Project12Application.class, args);
	}

}
