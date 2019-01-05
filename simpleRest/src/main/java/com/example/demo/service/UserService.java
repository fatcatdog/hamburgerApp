package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;


@Service("userService")
public class UserService {

	@Autowired
	private UserDao userDao;

	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email.toLowerCase());
	}

	public User findUserById(int id) {
		return userDao.findUserById(id);
	}

	
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	
	 public boolean checkIfEmailIsInDb(String email) {
		return userDao.checkIfEmailIsInDb(email.toLowerCase());
	}
}