package com.example.lombok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lombok.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
