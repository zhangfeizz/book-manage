package com.nowcoder.project.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	private static int COOKIE_AGE = 60 * 60 * 24 * 7;

	public static void writeCookie(String key, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(COOKIE_AGE);
		response.addCookie(cookie);
	}

	public static String getCookie(String key, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static void removeCookie(String key, HttpServletRequest request,
			HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();

		for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
			if ((key).equalsIgnoreCase(cookies[i].getName())) {

				Cookie cookie = new Cookie(key, "");
				cookie.setPath("/");
				cookie.setMaxAge(0);// 设置保存cookie最大时长为0，即使其失效
				response.addCookie(cookie);
			}
		}
	}

}
