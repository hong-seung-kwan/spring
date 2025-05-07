package com.example.demo.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Reservation;

@SpringBootTest
public class ReservationRepositoryTest {

	@Autowired
	ReservationRepository repository;
	
	@Test
	void 리파지토리확인() {
		System.out.println("repository: " + repository);
	}
	
	@Test
	void 데이터추가() {
		List<Reservation> list = new ArrayList<>();
		
		Reservation reservation1 = new Reservation(0, LocalDate.of(2024, 9, 5), "둘리", "010-1111-2222", 201);
		Reservation reservation2 = new Reservation(0, LocalDate.of(2024, 9, 5), "또치", "010-3333-4444", 202);
		Reservation reservation3 = new Reservation(0, LocalDate.of(2024, 9, 10), "도우너", null, 201);
		
		list.add(reservation1);
		list.add(reservation2);
		list.add(reservation3);
		
		repository.saveAll(list);
	}
}
