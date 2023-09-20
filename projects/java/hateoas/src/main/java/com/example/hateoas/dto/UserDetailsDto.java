package com.example.hateoas.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import com.example.hateoas.controller.UserController;
import com.example.hateoas.model.User;

public class UserDetailsDto extends RepresentationModel<UserDetailsDto> {

	private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
	private Boolean isActive;
	
	public UserDetailsDto(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.age = user.getAge();
		this.email = user.getEmail();
		this.isActive = user.getIsActive();
		this.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());
		this.add(linkTo(UserController.class).withRel("allUsers"));
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getIsActive() {
		return isActive;
	}
}