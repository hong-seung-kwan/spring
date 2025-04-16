package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
	
	@Test
	public void 데이터100개추가() {
		
		for(int i =1; i <= 100; i++) {
			// 메모 생성
			Memo memo = Memo.builder()
								.text("sample.."+i)
								.build();
			// memo 테이블에 데이터 추가
			repository.save(memo);
		}
	}
	
	@Test
	public void 페이지() {
		
		// 페이지 정보를 담은 Pageable 객체를 생성
		// of 함수를 사용해서 인스턴스 생성
		// 인자: 조회할 페이지의 번호와 페이지 안에 담을 데이터 개수
		
		// 페이지번호는 index로 0번부터 시작됨. 0번은 1페이지
		// 1페이지에 데이터 10개를 담아서 조회
		
		//
		Pageable pageable = PageRequest.of(0, 10);
		
		// memo 테이블 안에 있는 데이터를 조회
		// 1페이지에 있는 데이터만 조회
		Page<Memo> page = repository.findAll(pageable);
		
		// 페이지 조회를 하면 SQL에 limit 키워드가 추가됨
		// 첫번째 페이지를 조회했다면 limit 0, 10
		// 첫번째 행부터 10개를 조회한다는 의미
		
		// memo list
		List<Memo> list = page.getContent();
		for(Memo memo : list) {
			System.out.println(memo);
		}
		
		// page가 제공하는 여러가지 정보를 꺼내기
		System.out.println("총 페이지 개수: " + page.getTotalPages());
		System.out.println("현재 페이지 번호: " + page.getNumber());
		System.out.println("페이지당 데이터 개수: "+ page.getSize());
		System.out.println("다음 페이지가 있는지? " + page.hasNext());
		System.out.println("이 페이지가 첫페이지인지? " + page.isFirst());
	}
	// page 구조:
	// content: memo list
	// pageable: 페이지 정보
	// total: data 개수
	
	@Test
	public void 정렬() {
		// 정렬 조건 만들기
		// by: 기준컬럼
		// descending: 정렬 방식 (역정렬) 내림차순..
		
		// no 필드를 기준으로 역정렬
		Sort sort = Sort.by("no").descending();
		
		// 페이지 정보 생성
		// 인자: 페이지번호, 데이터개수, 정렬
		Pageable pageable = PageRequest.of(0, 10, sort);
		
		// 1번 페이지 조회
		Page<Memo> page = repository.findAll(pageable);
		
		// memo list만 꺼내기
		List<Memo> list = page.getContent();
		
		for(Memo memo : list) {
			System.out.println(memo);
		}
		
		// sort를 적용하면 sql에 order by가 추가됨
	}
	
	// 쿼리메소드 테스트
	
	@Test
	public void 범위검색() {
		
		// 10~20 사이의 메모를 검색
		List<Memo> list = repository.findByNoBetween(10, 20);
		
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
	@Test
	public void 비교검색() {
		List<Memo> list = repository.findByNoLessThan(10);
		
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
	@Test
	public void 빈값체크() {
		List<Memo> list = repository.findByTextIsNull();
		
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
	@Test
	public void 역정렬() {
		List<Memo> list = repository.findAllByOrderByNoDesc();
		System.out.println(list);
	}
	
	@Test
	public void 삭제() {
		repository.deleteMemoByNoLessThan(5);
	}
	
	@Test
	public void 어노테이션테스트1() {
		// 10번 아래 메모 검색
		List<Memo> list = repository.get1(10);
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
}
