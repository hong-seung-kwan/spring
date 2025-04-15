package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Memo;
import com.example.demo.repository.MemoRepository;

// MemoRepository의 기능을 확인하는 클래스
@SpringBootTest
public class MemoRepositoryTest {
	// MemoRepository를 상속받은 구현체 만든적 없음
	// component 붙여서 빈으로 등록한적 없음
	// jpa가 대신 처리함

	@Autowired
	MemoRepository repository;

	@Test
	public void 리파지토리확인() {
		System.out.println(repository);
	}

	@Test
	public void 데이터추가() {
		// 엔티티 생성
		Memo memo = new Memo(0, "새글입니다");
		repository.save(memo);
	}

	// save 함수를 호출하면 insert 쿼리가 생성됨

	@Test
	public void 데이터일괄등록() {

		// Memo 타입의 리스트 생성
		List<Memo> list = new ArrayList<>();

		// no와 text 모두 생략 가능
		Memo memo1 = Memo.builder().text("새글입니다").build();
		Memo memo2 = Memo.builder().build();
		list.add(memo1);
		list.add(memo2);

		repository.saveAll(list);
	}

	@Test
	public void 데이터단건조회() {
		// 단건조회/전체조회
		// ID(PK)를 사용해서 특정 데이터 조회
		// NO를 입력해서 Memo 꺼내기
		// find 함수를 호출하면 select 쿼리가 생성됨
		int no = 2;
		Optional<Memo> optional = repository.findById(no);
		// 값이 있는 경우에만 꺼내기
		if (optional.isPresent()) {
			Memo memo = optional.get();
			System.out.println(memo);
		} else {
			System.out.println(no + "번 데이터는 없습니다");
		}
		// 실제 값 꺼내기
	}

	// 전체조회 or 목록조회
	@Test
	public void 데이터전체조회() {

		// findall 함수를 호출하면 select 쿼리가 생성됨
		List<Memo> list = repository.findAll();
		System.out.println(list);

	}
	@Test
	public void 데이터수정() {
		
		// 수정할 데이터 준비
		
		// 잘못된 수정 방식
//		Memo memo = new Memo(5,"글이수정되었습니다~");
		
		// 데이터 추가 및 수정은 save 함수를 사용
		// 테이블에 1번이 있으면 update 없으면 insert 쿼리가 생성됨
		// pk 유무에 따라 분기됨
//		repository.save(memo);
		
		// 먼저 1번 데이터가 있는지 확인
		Optional<Memo> optional = repository.findById(5);
		// 1번에 데이터가 있으면 수정
		if(optional.isPresent()) {
			Memo memo = optional.get();
			// 기존 데이터로 부분 업데이트 
			memo.setText("글이수정되었습니다");
			repository.save(memo);
		}
	}
	
	// 단건삭제/전체삭제
	@Test
	public void 데이터삭제() {
		
		// id => pk(식별자)
		// no를 사용해서 특정 memo를 삭제
		
		// deleteById 함수는 delete 쿼리를 생성함
		repository.deleteById(1);
	}
	
	@Test
	public void 데이터전체삭제() {
		
		repository.deleteAll();
		
		// 1. select로 테이블 조회
		// 2. 데이터 건수만큼 delete
	}
}
