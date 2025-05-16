package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Member;
import com.example.demo.entity.Product;

public interface CartService {

	List<CartDTO> getListByUserId(String userId);

	int register(CartDTO cartDTO);

	boolean remove(int cartNo);

	default CartDTO entityToDto(Cart cart) {
		
		int productNo = cart.getProduct().getProductNo();
		
		String userId = cart.getUser().getUserId();
		
		CartDTO dto = CartDTO.builder()
									.cartNo(cart.getCartNo())
									.user(userId)
									.product(productNo)
									.product_quantity(cart.getProduct_quantity())
									.build();

		return dto;
	}

	default Cart dtoToEntity(CartDTO dto) {
		
		Product product = Product.builder().productNo(dto.getProduct()).build();
		
		Member id = Member.builder().userId(dto.getUser()).build();
		
		Cart cart = Cart.builder()
								.cartNo(0)
								.user(id)
								.product(product)
								.product_quantity(dto.getProduct_quantity())
								.build();
		return cart;
	}
}
