package com.sahay.ecombackendapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahay.ecombackendapi.entity.User;
import com.sahay.ecombackendapi.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
	private UserService userService;
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user){
		return new ResponseEntity<String>(userService.saveUser(user),HttpStatus.CREATED);
	}
	@PutMapping("{userId}")
	public ResponseEntity<String> updateUserInfo(@PathVariable Integer userId,@RequestBody User user){
		return new ResponseEntity<String>(userService.updateUser(userId, user),HttpStatus.ACCEPTED);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id){
		return new ResponseEntity<String>(userService.deleteUser(id),HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id){
	  return new ResponseEntity<User>(userService.getUser(id),HttpStatus.OK);	
	}
	@GetMapping("/by-email")
	public ResponseEntity<User> getUserByEmail(@RequestParam String email){
		return new ResponseEntity<User>(userService.getUserByEmail(email),HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
	}
}
