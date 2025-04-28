package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

public interface BoardService {

	// 게시물 등록 메소드
	int register(BoardDTO dto);

	// 게시물 목록조회 메소드
//	List<BoardDTO> getList();
	
	// 매개변수와 리턴타입 변경
	Page<BoardDTO> getList(int pageNumber);

	// 게시물 상세조회 메소드
	BoardDTO read(int no);

	// 게시물 수정 메소드
	void modify(BoardDTO dto);

	// 게시물 삭제 메소드
	void remove(int no);

	// default 키워드를 사용하여 일반메소드 만들기
	// dto를 엔티티로 변환하는 메소드
	// board entity의 구조가 바뀌었으므로 변환메소드도 수정
	default Board dtoToEntity(BoardDTO dto) {
		
		//시간 필드는 생략
		
		// dto의 writer(string)를 꺼내서 member 인스턴스로 생성
		
		String id = dto.getWriter();
		Member member = Member.builder().id(id).build();
		
		Board entity = Board.builder()
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		return entity;
	}

	// 엔티티를 dto로 변환하는 메소드
	// board entity의 구조가 바뀌었으므로 변환메소드도 함께 수정
	default BoardDTO entityToDto(Board entity) {
		
		// entity의 writer(member)를 꺼내서 string으로 변경
		
		Member member = entity.getWriter();
		
		// member id 꺼내기
		String id = member.getId();
		
		BoardDTO dto = BoardDTO.builder()
				.no(entity.getNo())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(id)
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();

		return dto;
	}

}
