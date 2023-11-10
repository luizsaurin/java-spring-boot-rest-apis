package com.example.baseh2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.baseh2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
