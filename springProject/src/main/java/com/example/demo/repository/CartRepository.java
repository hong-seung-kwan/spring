package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Cart;

import jakarta.transaction.Transactional;

@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Query(value = "SELECT * FROM CART WHERE USER_ID = :userId", nativeQuery = true)
	List<Cart> findByUserId(@Param("userId") String userId);
	
	@Modifying
	@Query(value = "DELETE FROM CART WHERE USER_ID = :userId", nativeQuery = true)
	void deleteByUserId(@Param("userId") String userId);
	
}
