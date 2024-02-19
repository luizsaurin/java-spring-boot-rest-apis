package com.example.rediscache.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rediscache.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
