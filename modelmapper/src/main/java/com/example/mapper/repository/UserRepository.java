package com.example.mapper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mapper.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
