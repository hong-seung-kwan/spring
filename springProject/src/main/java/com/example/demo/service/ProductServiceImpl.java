package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repository;
	
	@Override
	public int register(ProductDTO dto) {
		
		Product product = dtoToEntity(dto);
		repository.save(product);
		int newNo = product.getProductNo();
		
		return newNo;
	}

	@Override
	public ProductDTO read(int productNo) {
		
		Optional<Product> optional = repository.findById(productNo);
		
		if(optional.isPresent()) {
			Product product = optional.get();
			ProductDTO productDTO = entityToDto(product);
			return productDTO;
		} else {
			return null;
		}		
	}

	@Override
	public void modify(ProductDTO dto) {
		
		Optional<Product> optional = repository.findById(dto.getProductNo());
		
		if(optional.isPresent()) {
			Product product = optional.get();
			
			product.setName(dto.getName());
			product.setPrice(dto.getPrice());
			product.setContent(dto.getContent());
			product.setImageUrl(dto.getImageUrl());
			
			repository.save(product);
		}
		
	}

	@Override
	public void remove(int productNo) {
		
		Optional<Product> optional = repository.findById(productNo);
		if(optional.isPresent()) {
			repository.deleteById(productNo);
		}
	}

}
