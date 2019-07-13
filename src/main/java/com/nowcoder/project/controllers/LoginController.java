package com.nowcoder.project.controllers;

import javax.servlet.http.HttpServletResponse;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowcoder.project.biz.LoginBiz;
import com.nowcoder.project.model.User;
import com.nowcoder.project.utils.CookieUtils;

@Controller
public class LoginController {

	@Autowired
	private LoginBiz loginBiz;

	@RequestMapping(path= {"/users/register"}, method= {RequestMethod.GET})
	public String register() {
		return "login/register";
	}

	@RequestMapping(path = {"/users/register/do"}, method = {RequestMethod.POST})
	public String doRegister( 
			Model model, HttpServletResponse response,
			@RequestParam("name") String name , 
			@RequestParam("email") String email, 
			@RequestParam("password") String password) {

		User user = new User();
		user.setName(name)
		.setEmail(email)
		.setPassword(password);

		try {
			String t = loginBiz.register(user);  //验证注册
			CookieUtils.writeCookie("t", t, response);
			return "redirect:/index";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "404";
		}
	}

	@RequestMapping(path= {"/user/login"}, method= {RequestMethod.GET})
	public String login() {
		return "login/login";
	}

	@RequestMapping(path = {"/users/login/do"}, method = {RequestMethod.POST})
	public String doLogin(@PathParam("email") String email ,
			@PathParam("password") String password, 
			HttpServletResponse response, Model model) {
		try {
			String t = loginBiz.login(email, password);
			CookieUtils.writeCookie("t", t, response);
			return "redirect:/index";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "404";
		}
	}

	@RequestMapping(path = {"/users/logout/do"}, method = {RequestMethod.GET})
	public String doLogout(@CookieValue("t") String t) {
		loginBiz.logout(t);
		return "redirect:/index";
	}

}






























