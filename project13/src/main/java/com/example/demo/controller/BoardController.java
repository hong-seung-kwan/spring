package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;


// @controller + @responsebody
// @RestController 아에 @responsebody가 포함되어 있으므로
// 해당 컨트롤러 안에 있는 메소드는 모두 데이터를 text 또는 json으로 반환
@RestController
@RequestMapping("/board") // 중간경로
public class BoardController {

	@Autowired
    BoardService service;

	// 전체경로: /board/register
	// 반환값: ResponseEntity ( 응답코드와 바디데이터 )
	// 매개변수: 새로운 게시물 데이터
	// 파라미터로 게시물 데이터를 수집
	// 파라미터의 형태:
	// 1. pathvariable/requestparam : URL 주소에 포함됨
	// 2. request body: 메세지 바디에 담기 (json 또는 xml 형태)
	// 3. 폼데이터: html문서의 form태그로 넘어옴
	
	// 외부에서 게시물데이터를 json 또는 xml 형태로 전달
	// @RequestBody에 의해서 dto 클래스로 변환
	
	@PostMapping("/register") // 마지막 경로
	public ResponseEntity<Integer> register(BoardDTO dto) {// 게시물 데이터를 폼데이터 방식으로 수집
		dto.setWriter("user"); // 임시 아이디
		int no = service.register(dto);
		// 201성공코드와 새로운 글번호를 반환한다
		return new ResponseEntity<>(no, HttpStatus.CREATED); 
	}

	//localhost:8080/board/list
	@GetMapping("/list")
	public ResponseEntity<List<BoardDTO>> getList() {
		List<BoardDTO> list = service.getList();
		return new ResponseEntity<>(list, HttpStatus.OK); //200성공코드와 게시물목록을 반환한다
	}

	//localhost:8080/board/read?no=1
	@GetMapping("/read")
	public ResponseEntity<BoardDTO> read(@RequestParam(name = "no") int no) {
		BoardDTO dto = service.read(no);
		return new ResponseEntity<>(dto, HttpStatus.OK); //200성공코드와 게시물정보를 반환한다
	}

	//localhost:8080/board/modify + 바디(JSON데이터)
	@PutMapping("/modify")
	public ResponseEntity modify(@RequestBody BoardDTO dto) {
		 service.modify(dto);
		 return new ResponseEntity(HttpStatus.NO_CONTENT); //204성공코드를 반환한다
	}

	//localhost:8080/board/remove?no=1
	@DeleteMapping("/remove")
	public ResponseEntity remove(@RequestParam(name = "no") int no) {
		service.remove(no);
		return new ResponseEntity(HttpStatus.NO_CONTENT); //204성공코드를 반환한다
	}

}
