package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Gift;

public interface GiftRepository extends JpaRepository<Gift, Integer> {
	// 가격이 50000원 이상인 선물 검색
	@Query(value = "select * from tbl_gift where price >=:price", nativeQuery = true)
	List<Gift> get1(@Param("price") int price);
	
	// 상품명이 '세트'로 끝나는 데이터 검색
	@Query(value = "select * from tbl_gift where name like %:name", nativeQuery = true)
	List<Gift> get2(@Param("name") String name);
	
	// 선물 가격 40000원 이하 품목 생활용품인 데이터 검색
	@Query(value = "select * from tbl_gift where price <= :price AND type = :type", nativeQuery = true)
	List<Gift> get3(@Param("price") int price, @Param("type") String type);
	
}
