package com.example.demo.order.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.order.entity.Stats;

public interface StatsRepository extends JpaRepository<Stats, LocalDate> {
	
	
}
