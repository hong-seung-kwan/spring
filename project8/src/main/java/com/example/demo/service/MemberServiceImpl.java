package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

// @Component를 포함하고 있음
// @Component: 컨테이너에 빈으로 등록
// 해당 클래스가 서비스임을 명시
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository repository;
	
	// 특정 페이지에 있는 회원 리스트를 조회
	// 예상 페이지 번호: 1~3
	@Override
	public Page<MemberDTO> getList(int page) {
		
		// 페이지 번호를 인덱스로 변경
		int pageIndex = (page == 0) ? 0 : page-1;
		
		// 페이지 조건 생성
		// 회원가입 날짜를 기준으로 역정렬
		Sort sort = Sort.by("regDate").descending();
		
		// 인자: 페이지번호, 한 페이지에 표시할 데이터개수, 정렬
		Pageable pageable = PageRequest.of(pageIndex, 10, sort);
		
		// 검색
		Page<Member> entityPage =  repository.findAll(pageable);
		
		// entity 타입의 page를 dto 타입의 page로 변환
		Page<MemberDTO> dtoPage = null;
		
		dtoPage = entityPage.map(entity -> entityToDto(entity));
		
		return dtoPage;
	}

	/* 게시물 테이블의 pk(no)는 자동으로 생성되었음
	 * 하지만 회원 테이블의 pk(id)는 수동으로 입력됨
	 * 회원의 아이디가 중복될 경우에는 회원가입 실패
	 * */
	
	
	
	@Override
	public boolean register(MemberDTO dto) {
		
		// 먼저 회원의 아이디가 중복이 아닌지 확인
		String id = dto.getId();
		
		// 해당아이디가 존재하는지 확인
		Optional<Member> optional = repository.findById(id);
		// 아이디가 이미 사용중이라면 처리결과는 false로 반환
		if(optional.isPresent()) {
			System.out.println("이미 사용중인 아이디입니다..");
			return false; // 회원가입 실패 의미
		}else {
			// 아이디 사용할 수 있으면 회원가입 진행
			
			// dto -> entity
			Member entity = dtoToEntity(dto);
			
			// 테이블에 새로운 회원을 추가
			repository.save(entity);
			
			return true; // 회원가입에 성공 의미
		}
	}

	@Override
	public MemberDTO read(String id) {
		Optional<Member> optional = repository.findById(id);
		if(optional.isPresent()) {
			Member entity = optional.get();
			MemberDTO dto =  entityToDto(entity);
			return dto;
		}
		
		return null;
	}
	
	
}
