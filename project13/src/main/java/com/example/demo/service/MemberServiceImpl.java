package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

//@Service
public class MemberServiceImpl implements MemberService {

	// 회원 가입 메소드 수정
	
	@Autowired
	private MemberRepository repository;
	
	// 회원가입시 필요한 인코더 선언
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<MemberDTO> getList() {
		
		List<Member> entityList = repository.findAll();		
		
		List<MemberDTO> dtoList = entityList.stream()
				.map(entity -> entityToDto(entity))
				.collect(Collectors.toList());

		return dtoList;
	}
	
	@Override
	public boolean register(MemberDTO dto) {
		String id = dto.getId();
		MemberDTO getDto = read(id);
		if (getDto != null) {
			System.out.println("사용중인 아이디입니다.");
			return false;
		}
		Member entity = dtoToEntity(dto);

		// 패스워드 인코더로 패스워드 암호화하기
		String enpw = encoder.encode(entity.getPassword());
		entity.setPassword(enpw);

		repository.save(entity);
		return true;
	}

	@Override
	public MemberDTO read(String id) {
		Optional<Member> result = repository.findById(id);
		if (result.isPresent()) {
			Member member = result.get();
			return entityToDto(member);
		} else {
			return null;
		}
	}

}
