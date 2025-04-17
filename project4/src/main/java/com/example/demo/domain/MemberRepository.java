package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 스프링 mvc 없이 순수한 자바 클래스로 회원 데이터를 관리하는 기능
// 데이터베이스 대신 Map 자료구조를 사용
public class MemberRepository {
	// 회원정보를 저장할 저장소
	// key: no, value: member
	// 하나만 생성해서 공유
	static public Map<Integer, Member> store = new HashMap<>();
	
	// 회원번호를 생성하기 위한 시퀀스
	static int sequence = 0;
	
	
	// 새로운 회원을 저장소에 추가
	// 매개변수: 새로운 회원 정보
	// 리턴값: 새로 생성된 회원정보
	public Member save(Member member) {
		// 회원이 추가될때마다 시퀀스를 1씩 증가
		sequence++;
		member.setNo(sequence);
		store.put(sequence,member);
		return member;
	}
	
	// 단건 조회
	// 매개변수: 회원번호
	// 반환값: 회원정보
	public Member findById(int no) {
		Member member = store.get(no);
		
		return member;
		
	}
	
	// 목록조회
	public List<Member> findAll(){
		Collection<Member> list = store.values();
		// Collection -> list
		List<Member> arrayList = new ArrayList<>(list);
		return arrayList;
	}
	
	// 삭제
	public void clearStore() {
		// 저장소의 모든 데이터를 삭제
		store.clear();
	}
}