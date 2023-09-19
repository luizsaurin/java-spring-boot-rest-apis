package com.example.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.swagger.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
