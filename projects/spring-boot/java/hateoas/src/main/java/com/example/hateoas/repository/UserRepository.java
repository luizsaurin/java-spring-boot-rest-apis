package com.example.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hateoas.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
