package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;

// 서비스:
// 1. 컨트롤러와 리파지토리 사이에서 데이터를 변환
// 2. 비즈니스 로직을 처리


public interface MemberService {
	
	// 변환메소드는 일반함수로 작성할 것
	// 자식 클래스에서 공통으로 사용하기 때문에
	// 인터페이스에 일반 메소드 만들기: default 키워드
	
	// 회원목록 조회
	// 매개변수: 페이지번호
	// 리턴값: 특정 페이지에 있는 회원 리스트
	Page<MemberDTO> getList(int page);
	
	// 회원가입
	// 매개변수:
	// 반환값: 처리결과
	boolean register(MemberDTO dto);
	
	// 회원 상세 조회
	// 매개변수: 아이디
	// 반환값: 특정 회원의 정보
	MemberDTO read(String id);
	
	// entity -> dto
	default MemberDTO entityToDto(Member member) {
		
		MemberDTO dto = MemberDTO.builder()
									.id(member.getId())
									.name(member.getName())
									.password(member.getPassword())
									.regDate(member.getRegDate())
									.modDate(member.getModDate())
									.build();
		
		return dto;
	}
	
	// dto -> entity
	// 매개변수: dto
	// 리턴값: entity
	default Member dtoToEntity(MemberDTO dto) {
		// 회원 데이터 중에서 날짜는 왜 없지..?
		// 이 변환 메소드를 사용하는 시점: 새로운 회원을 등록할 때
		Member member = Member.builder()
								.id(dto.getId())
								.name(dto.getName())
								.password(dto.getPassword())
								.build();
		
		return member;
	}
}
