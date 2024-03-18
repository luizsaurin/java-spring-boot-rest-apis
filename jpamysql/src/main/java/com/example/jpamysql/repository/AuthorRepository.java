package com.example.jpamysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpamysql.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	// Named query
	Author findByName(String authorName);

	// JPQL query
	@Query("SELECT a FROM Author a WHERE a.name = :authorName")
	Author findByNameJPQL(String authorName);
	
	// Native query
	@Query(value = "SELECT * FROM author WHERE author.name = :authorName", nativeQuery = true)
	Author findByNameNative(String authorName);
	
}
