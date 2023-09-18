package com.example.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.base.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
