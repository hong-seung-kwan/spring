package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;

@Component
public class CommentServiceImlp implements CommentService {

	@Autowired
	CommentRepository repository;
	
	
	@Override
	public List<CommentDTO> getListByBoardNo(int boardNo) {
		
		List<Comment> list = repository.findByBoardNo(boardNo);
		
		// entity list -> dto list
		List<CommentDTO> dtolist = new ArrayList<>();
		
		for(Comment entity : list) {
			CommentDTO dto = entityToDto(entity);
			dtolist.add(dto);
		}
		
		return dtolist;
	}

	@Override
	public int register(CommentDTO dto) {
		// dto -> entity
		Comment entity = dtoToEntity(dto);
		
		// 테이블에 새로운 데이터 추가
		repository.save(entity);
		
		// 새로운 댓글 번호 반환
		int commentNo = entity.getCommentNo();
		
		return commentNo;
	}

	@Override
	public boolean remove(int no) {
		
		// 테이블에 댓글이 있는지 확인
		Optional<Comment> optional = repository.findById(no);
		// 댓글이 있으면 삭제 진행
		if(optional.isPresent()) {
			
			repository.deleteById(no);
			return true; // 삭제 성공 의미
		}

		return false; // 삭제 실패 의미
	}
	
}
