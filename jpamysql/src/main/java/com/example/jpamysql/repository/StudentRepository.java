package com.example.jpamysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpamysql.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
