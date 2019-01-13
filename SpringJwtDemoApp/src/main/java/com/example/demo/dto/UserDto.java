package com.example.demo.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
	private Long id;

	private String name;

	private String username;

	private String email;

	private Instant createdAt;
	
	private Instant updatedAt;
	
	private Set<Role> roles = new HashSet<>();
	
}
