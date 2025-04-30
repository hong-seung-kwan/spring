package com.example.demo.job;

import java.time.LocalDate;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.Stats;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.order.repository.StatsRepository;

// 배치 작업을 등록하는 클래스

// config 클래스로 설정
// 실행순서: config -> 일반 클래스
@Configuration
public class SimpleJobConfig {
	
	// 배치 실행 이력을 데이터베이스에 저장하는 컴포넌트
	// 배치가 언제 실행됬는지, 성공했는지 실패했는지 기록
	@Autowired
	JobRepository jobRepository;
	
	// 배치 작업 중 데이터베이스 트랜잭션을 처리하는 컴포넌트
	// commit, rollback
	@Autowired
	PlatformTransactionManager manager;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	StatsRepository statsRepository;
	
	// 배치 작업 등록
	@Bean
	public Job simplejob1() {
				
		// 인자: 작업명, 리파지토리
		// job에 첫번째 step을 등록
		// job에 두번째 step을 등록
		// job에 세번째 step을 등록
		// 순서: step1 -> step2 -> step3
		
		return new JobBuilder("simplejob", jobRepository)
					.start(step1())
					.next(step2())
					.next(step3())
					.build();
									
	}
	
	// 배치에 등록할 작업을 먼저 생성	
	@Bean
	public Step step1() {
		
		TaskletStep step = new StepBuilder("step1..", jobRepository)
									.tasklet(testTasklet(), manager)
									.build();
		
		return step;
	}
	
	// 두번째 step 생성
	@Bean
	public Step step2() {
		
		// step에 task 등록
		TaskletStep step = new StepBuilder("step2..", jobRepository)
								.tasklet(testTasklet2(), manager)
								.build();
		
		return step;
	}
	
	// 세번째 step 생성
	@Bean
	public Step step3() {
		
		TaskletStep step = new StepBuilder("step3..", jobRepository)
								.tasklet(testTasklet3(), manager)
								.build();
		return step;
	}
	
	
	// 작업(step)에 등록할 Task를 먼저 생성
	@Bean
	public Tasklet testTasklet() {
		
		Tasklet tasklet = (contribution, chunkContext) -> { 
			
			System.out.println("Step1. 주문건수와 총금액 계산하기");

			// 주문 이력 조회
			
			// 날짜 생성
			LocalDate now = LocalDate.now();
			
			// 주문 이력 조회
			List<Order> list = orderRepository.findByOrderDate(now);
			
			// 총 주문 건수 구하기
			long count = list.stream().count();
			
			// 총 주문 금액 구하기
			// order -> order.price
			int totalPrice =  list.stream()
					.mapToInt(order-> order.getPrice())
					.sum();
			
			// 배치 context 생성
			// context: 배치 작업 중에 생성되는 데이터를 공유하기 위한 저장소
			StepContext context = chunkContext.getStepContext();
			ExecutionContext eContext = context.getStepExecution()
											   .getJobExecution()
											   .getExecutionContext();
			
			// 위에서 구한 건수와금액을 저장소에 넣기
			// key, value
			eContext.put("count", count);
			eContext.put("totalPrice", totalPrice);
			
			
			return RepeatStatus.FINISHED;
			
		};
		
		
		return tasklet;
	}
	
	// step에 등록할 두번째 task를 생성
	@Bean
	public Tasklet testTasklet2() {
		
		Tasklet tasklet = (contribution, chunkContext) -> { 
			
			System.out.println("Step2. 통계 테이블에 데이터 추가하기");
			
			// 이전 스텝에서 생성한 데이터 꺼내기
			
			// context 생성
			StepContext context = chunkContext.getStepContext();
			ExecutionContext eContext = context.getStepExecution()
											   .getJobExecution()
											   .getExecutionContext();
			
			// 배치 저장소에서 주문건수 꺼내기
			Object obj1 = eContext.get("count");
			// object -> long
			String str1 = obj1.toString();
			long count = Long.parseLong(str1);
			
			// 배치 저장소에서 총금액 꺼내기
			Object obj2 = eContext.get("totalPrice");
			// object -> int
			String str2 = obj2.toString();
			int totalPrice = Integer.parseInt(str2);
			
			// 결과 확인
			System.out.println("총 주문 건수: " + count);
			System.out.println("총 주문 금액: " + totalPrice);
			
			// 날짜 생성
			LocalDate date = LocalDate.now();
			
			// 통계 데이터 생성
			Stats stats = Stats.builder()
									.orderDt(date)
									.count(count)
									.totalPrice(totalPrice)
									.build();
			
			// 테이블에 통계 데이터 추가
			statsRepository.save(stats);
			
			
			
			return RepeatStatus.FINISHED;
		};
		
		return tasklet;
	}
	
	// step에 등록할 세번째 task를 생성
	@Bean
	public Tasklet testTasklet3() {
		
		Tasklet tasklet = ( contribution, chunkContext) -> {
			
			System.out.println("Step3. 전날 주문 이력 삭제");
			
			// 날짜 생성
			LocalDate now = LocalDate.now();
			LocalDate date = now.minusDays(1);
			
			// order 테이블에서 지난 이력을 삭제
			orderRepository.removeByOrderDate(date);
						
			return RepeatStatus.FINISHED;
			
		};
		return tasklet;
	}
	
}


