package com.example.demo.job;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleScheduler {
	
	// 정해진 시간에 함수를 실행
	// cron: 실행주기
	// 1초 간격으로 함수가 실행됨
	@Scheduled(cron = "0/10 * * * * *")
	public void test() {
		LocalDateTime now = LocalDateTime.now();
		System.out.println("run.." + now);
	}
	
}
