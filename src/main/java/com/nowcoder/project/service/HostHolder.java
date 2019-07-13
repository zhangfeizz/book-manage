package com.nowcoder.project.service;

import org.springframework.stereotype.Service;

import com.nowcoder.project.model.User;
import com.nowcoder.project.utils.ConcurrentUtils;

@Service
public class HostHolder {
	public User getUser() {
		return ConcurrentUtils.getHost();
	}
	
	public void setUser(User user) {
		ConcurrentUtils.setHost(user);
	}
}
