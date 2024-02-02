package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	UserDetails findByUsername(String user);
	
}
