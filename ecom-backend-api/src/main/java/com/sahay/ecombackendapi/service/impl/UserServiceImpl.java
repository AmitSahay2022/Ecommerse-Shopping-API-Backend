package com.sahay.ecombackendapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sahay.ecombackendapi.entity.User;
import com.sahay.ecombackendapi.exception.UserAllreadyExistException;
import com.sahay.ecombackendapi.exception.UserNotFoundException;
import com.sahay.ecombackendapi.repository.UserRepository;
import com.sahay.ecombackendapi.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository; 
	@Override
	public String saveUser(User user) {
		
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new UserAllreadyExistException("This Email is Already Registered");
		}
		else {
			userRepository.save(user);
		}
		return "User Registered Successfully";
	}

	@Override
	public String deleteUser(Integer id) {
		
		User user = getUser(id);
		userRepository.delete(user);
		return "User Deleted Successfully";
	}

	@Override
	public String updateUser(Integer userId, User usr) {
		// TODO Auto-generated method stub
		User user = getUser(userId);
		user.setFirstName(usr.getFirstName());
		user.setLastName(usr.getLastName());
		user.setPassword(usr.getPassword());
		userRepository.save(user);
		return "Updated Successfully";
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		User user = userRepository
				         .findById(id)
				         .orElseThrow(()->new UserNotFoundException("User Not Found!"));
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository
				.findByEmail(email)
				.orElseThrow(()->new UserNotFoundException("User Not Found With Given Email"));
	}

}
