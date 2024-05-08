package com.example.flyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flyway.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
