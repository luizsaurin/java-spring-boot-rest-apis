package com.example.jpamysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpamysql.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
}
