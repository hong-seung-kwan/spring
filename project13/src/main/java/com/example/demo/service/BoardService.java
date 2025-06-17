package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

public interface BoardService {

	int register(BoardDTO dto);

	List<BoardDTO> getList();

	BoardDTO read(int no);

	void modify(BoardDTO dto);

	void remove(int no);

	default Board dtoToEntity(BoardDTO dto) {
		// entity의 writer 컬럼은 class
		// dto의 writer 필드는 string
		// 자료형이 일치하지 않아서 에러남
		
		// Member 인스턴스 생성
		// member를 등록할때는 모든 데이터를 넣어야 하지만
		// 참조를 위해서 member를 생성할때는 pk만 넣으면됨
		Member member = Member.builder().id(dto.getWriter()).build();
		
		Board entity = Board.builder()
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		return entity;
	}

	default BoardDTO entityToDto(Board entity) {
		// entity의 writer 컬럼: member class
		// dto의 writer 필드: string
		// 두 필드의 자료형이 달라서 에러남
		
		BoardDTO dto = BoardDTO.builder()
				.no(entity.getNo())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter().getId())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.imgPath(entity.getImgPath())
				.build();

		return dto;
	}

}
