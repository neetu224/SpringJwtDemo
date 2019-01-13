package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping(value="/user/{userId}")
	@PreAuthorize("hasRole('USER') and hasRole('ADMIN')")
	public UserDto getUserById(@PathVariable("userId")Long userId){
		User user=userService.getUserById(userId).orElse(null);
		return userMapper.userDomainToDto(user);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value="/users")
	public List<UserDto> getAllUsers(){
		return userService.getAllUsers().stream().map(user->userMapper.userDomainToDto(user)).collect(Collectors.toList());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value="/user/{userId}")
	public void deleteUser(@PathVariable("userId")Long userId){
		userService.deleteUser(userId);
	}
	
}
