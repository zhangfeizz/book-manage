package com.nowcoder.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowcoder.project.dao.UserDAO;
import com.nowcoder.project.model.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public int addUser(User user) {
		return userDAO.addUser(user);
	}

	public User getUser(String email) {
		return userDAO.selectByEmail(email);
	}

	public User getUser(int uid) {
		return userDAO.selectById(uid);
	}

}
