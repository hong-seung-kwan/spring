package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Members;

public interface MembersRepository extends JpaRepository<Members, String> {

}
