package com.example.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchScheduler {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job simpleJob1;
	
	// 배치작업을 일정 주기로 실행
	// cron : 실행주기
	// 10초 간격으로 배치를 실행
	@Scheduled(cron = "0/10 * * * * *")
	public void runBatch() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		// 파라미터: job의 실행이력을 관리하기 위해 현재시간을 전달
		JobParameters parameters = new JobParametersBuilder()
									   .addLong("time", System.currentTimeMillis())
									   .toJobParameters();
		
		jobLauncher.run(simpleJob1, parameters);
		
	}
	
}
