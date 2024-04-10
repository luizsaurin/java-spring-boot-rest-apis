package com.example.junit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.junit.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
