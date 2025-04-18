package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRepository;

// MVC 패턴으로 회원 관리 프로그램 만들기

// C: Controller
// Controller란? 사용자의 요청을 처리하는 컴포넌트
// 오직 요청만 처리. 화면X

@Controller
@RequestMapping("/v2") // url 경로 - 중간 경로
public class MemberController {
	
	// 회원등록폼을 반환하는 메소드
	@GetMapping("/form") // url 경로 - 마지막 경로
	// 이 함수의 전체 경로: /v2/form
	public String method1() {
		return "/v2/form"; // jsp 파일의 경로
	}
	// 함수 호출 URL: localhost:8080/v2/form
	
	// 회원 등록을 처리하는 메소드
	// 폼에서 전달받은 회원데이터를 저장소에 등록하고 처리 결과를 반환
	// 전달받은 값은 파라미터로 처리
	
	// Model: 컨트롤러에서 뷰로 데이터를 전달해주는 객체
	@PostMapping("/save")
	public String method2(@RequestParam(name = "userId") String userId,@RequestParam(name = "password") String password, Model model) {
		
		
		// 전달받은 파라미터로 회원 데이터 생성
		Member member = Member.builder().userId(userId).password(password).build();
		
		// 리파지토리 생성
		MemberRepository repository = new MemberRepository();
		// 리파지토리에 등록
		Member newMember = repository.save(member);
		System.out.println(newMember);
		
		// 등록 결과를 화면에 전달
		model.addAttribute("member", newMember);
		// 반환값: jsp 파일 경로
		return "/v2/save";
	}
}
