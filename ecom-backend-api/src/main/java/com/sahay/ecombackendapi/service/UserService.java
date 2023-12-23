package com.sahay.ecombackendapi.service;

import java.util.List;

import com.sahay.ecombackendapi.entity.User;

public interface UserService {
	String saveUser(User user);

	String deleteUser(Integer id);

	String updateUser(Integer userId, User user);

	User getUser(Integer id);

	List<User> getAllUsers();
	
	User getUserByEmail(String email);
}
