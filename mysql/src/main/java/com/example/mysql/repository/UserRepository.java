package com.example.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mysql.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
