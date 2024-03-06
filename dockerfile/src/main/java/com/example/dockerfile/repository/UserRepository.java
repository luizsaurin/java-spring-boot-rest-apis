package com.example.dockerfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dockerfile.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
