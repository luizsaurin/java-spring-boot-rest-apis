package com.example.lombok.model;

import java.io.Serializable;

import com.example.lombok.dto.UserCreationDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	// Serialization

	private static final long serialVersionUID = 1L;

	// Attributes

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private Boolean isActive;

	// Constructors

	public User(UserCreationDto dto) {
		this.firstName = dto.getFirstName();
		this.lastName = dto.getLastName();
		this.age = dto.getAge();
		this.email = dto.getEmail();
		this.isActive = dto.getIsActive();
	}
}
