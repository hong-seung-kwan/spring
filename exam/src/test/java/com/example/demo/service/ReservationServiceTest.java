package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.ReservationDTO;

@SpringBootTest
public class ReservationServiceTest {

	@Autowired
	ReservationService service;
	
	@Test
	void 서비스확인() {
		System.out.println("service: " + service);
	}
	@Test
	void 예약정보등록() {
		ReservationDTO dto = new ReservationDTO(0, LocalDate.of(2024, 9, 10), "고길동", "010-1234-5678", 202);
		service.register(dto);
	}
	@Test
	void 예약정보목록조회() {
		List<ReservationDTO> list = service.getList();
		
		for(ReservationDTO dto : list) {
			System.out.println(dto);
		}		
	}
	
	@Test
	void 예약정보단건조회() {
		ReservationDTO dto = service.read(1);
		
		System.out.println(dto);
	}
}
