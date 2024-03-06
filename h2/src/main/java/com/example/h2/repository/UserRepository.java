package com.example.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.h2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
