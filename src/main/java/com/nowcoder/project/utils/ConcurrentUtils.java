package com.nowcoder.project.utils;

import com.nowcoder.project.model.User;

public class ConcurrentUtils {

	private static ThreadLocal<User> host = new ThreadLocal<>();
	
	public static void setHost(User user) {
		host.set(user);
	}

	public static User getHost() {
		return host.get();
	}
}
