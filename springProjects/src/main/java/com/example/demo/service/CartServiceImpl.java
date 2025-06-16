package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDTO;
import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository repository;
	
	@Override
	public List<CartDTO> getListByUserId(String userId) {
		
		List<Cart> list = repository.findByUserId(userId);
		
		List<CartDTO> dtolist = new ArrayList<>();
		
		for(Cart cart : list) {
			CartDTO dto = entityToDto(cart);
			dtolist.add(dto);
		}
		
		return dtolist;
	}

	@Override
	public int register(CartDTO dto) {
		
		Cart cart = dtoToEntity(dto);
		
		repository.save(cart);
		
		int cartNo = cart.getCartNo();
		
		return cartNo;
	}

	@Override
	public boolean remove(int cartNo) {
		
		Optional<Cart> optional = repository.findById(cartNo);
		if(optional.isPresent()) {
			repository.deleteById(cartNo);
			return true;
		}
		
		return false;
	}

}
