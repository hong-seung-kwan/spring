package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

// controller: 사용자 요청을 처리하는 컴포넌트

//@RequestMapping("/member") // 중간경로
@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	// 목록화면을 반환하는 메소드
	// /member/list
	// /member/list?page=1
	// URL: /member/list?page=1
	// 페이지 번호를 파라미터로 수집
	@GetMapping("/member/list")
	public void list(Model model, @RequestParam(name = "page", defaultValue = "0"  )int page) {
		// 반환값: member 폴더 아래 list.html
		
		// 회원 목록 조회
		// 특정 페이지 조회
		Page<MemberDTO> list = service.getList(page);
		
		// 화면에 데이터 전달
		model.addAttribute("list",list);		
	}
	
	// 회원가입 화면을 반환하는 메소드
	// 반환값: member 폴더 아래 register.html
	@GetMapping("/register")
	public String register() {
		return "member/register";
	}
	
	// 새로운 회원을 추가하는 메소드
//	RedirectAttributes: 리다이렉트를 할때 화면에 데이터를 전달하는 객체
	// 회원가입에 성공하면 메인화면으로 이동
	// 회원 목록화면은 관리자만 접근 가능
	// 일반회원은 볼 수 없음
	@PostMapping("/register")
	public String registerPost(MemberDTO dto, RedirectAttributes attributes) {
		
		// 새로운 회원 추가
		boolean result = service.register(dto);
		
		// 등록에 성공했으면 목록화면으로
		// 그렇지 않으면 실패메세지 표시
		if(result) {
			return "redirect:/";
		}else{
			// 실패했으면 다시 등록화면으로 돌아가기
			// 이때 실패메시지 전달
			attributes.addFlashAttribute("msg","아이디가 중복되어 등록에 실패하였습니다..");
			return "redirect:/register";
		}
		
	}
	// 회원 상세 화면을 반환하는 메소드
	// 파라미터로 회원아이디를 수집
	// URL: /member/read?id=user1
	// 파라미터로 페이지번호 수집
	@GetMapping("/member/read")
	public void read(Model model, @RequestParam(name="id") String id, @RequestParam(name="page",defaultValue = "0") int page) {
		
		// 아이디로 특정 회원 정보 조회
		MemberDTO dto = service.read(id);
		
		// 화면에 회원 데이터 전달
		model.addAttribute("dto",dto);
		
		// 화면에 페이지번호 전달
		model.addAttribute("page",page);
		
	}
	
}
