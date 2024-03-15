package com.example.jpamysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpamysql.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
}
