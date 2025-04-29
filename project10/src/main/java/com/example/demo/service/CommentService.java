package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Member;

// Service:
// 1. 비즈니스 로직을 처리
// 2. 컨트롤러와 리파지토리 사이에서 데이터를 변환

public interface CommentService {
	
	// 게시물별 댓글 목록 조회
	// 매개변수: 게시물번호
	// 반환값: 댓글 목록
	List<CommentDTO> getListByBoardNo(int boardNo);
	
	// 댓글 등록
	// 매개변수: 새로운 댓글 정보
	// 반환값: 새로운 댓글 번호
	int register(CommentDTO dto);
	
	// 댓글 삭제
	// 매개변수: 댓글 번호
	// 반환값: 처리 결과
	boolean remove(int no);
	
	
	// dto -> entity
	// 매개변수: dto
	// 리턴값: entity
	default Comment dtoToEntity(CommentDTO dto) {
		
		// entity와 dto의 구조가 다르기 때문에
		// 게시물번호와 작성자는 엔티티로 변경해야함
		
		// 게시물 번호를 엔티티로 생성
		Board board = Board.builder().no(dto.getBoardNo()).build();
		
		// 아이디를 엔티티로 생성
		Member member = Member.builder().id(dto.getWriter()).build(); 
		
		Comment entity = Comment.builder().commentNo(0).board(board).content(dto.getContent()).writer(member).build();
		
		return entity;
	}
	
	// entity -> dto
	// 매개변수: entity
	// 리턴값: dto
	default CommentDTO entityToDto(Comment entity) {
		
		
		// entity와 dto의 구조가 다르기 때문에
		// 게시물번호와 작성자를 꺼내는 부분은 값을 한번 더 꺼내야함
		
		int boardNo = entity.getBoard().getNo();
		
		String id = entity.getWriter().getId();
		
		CommentDTO dto = CommentDTO.builder()
									.commentNo(entity.getCommentNo())
									.boardNo(boardNo)
									.content(entity.getContent())
									.writer(id)
									.regDate(entity.getRegDate())
									.modDate(entity.getModDate())
									.build();
		
		return dto;
	}
}
