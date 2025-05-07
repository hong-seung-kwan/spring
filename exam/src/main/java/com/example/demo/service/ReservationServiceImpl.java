package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository repository;
	
	@Override
	public int register(ReservationDTO dto) {
		
		Reservation entity = dtoToEntity(dto);
		repository.save(entity);
		int no = entity.getNo();
			
		return no;
	}

	@Override
	public ReservationDTO read(int no) {
		
		Optional<Reservation> optional = repository.findById(no);
		
		if(optional.isPresent()) {
			Reservation reservation = optional.get();
			ReservationDTO dto = entityToDto(reservation);
			return dto;
		} else {
			return null;
		}
				
	}

	@Override
	public List<ReservationDTO> getList() {
		
		List<Reservation> list = repository.findAll();
		List<ReservationDTO> listDTO = new ArrayList<>();
		
		listDTO = list.stream()
					.map(entity -> entityToDto(entity))
					.collect(Collectors.toList());
						
		
		return listDTO;
	}

}
