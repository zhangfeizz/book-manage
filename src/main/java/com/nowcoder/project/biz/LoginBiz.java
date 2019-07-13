package com.nowcoder.project.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.nowcoder.project.model.Ticket;
import com.nowcoder.project.model.User;
import com.nowcoder.project.model.exceptions.LoginRegisterException;
import com.nowcoder.project.service.TicketService;
import com.nowcoder.project.service.UserService;
import com.nowcoder.project.utils.ConcurrentUtils;
import com.nowcoder.project.utils.MD5;
import com.nowcoder.project.utils.TicketUtils;
@Service
public class LoginBiz {

	@Autowired
	private UserService userService;
	
	 @Autowired
	  private TicketService ticketService;
	/**
	 * 注册一个用户，返回用户的t票
	 * @param user
	 * @return
	 */
	public String register(User user) {
		//信息检查
		if (userService.getUser(user.getEmail()) != null) {
			throw new LoginRegisterException("用户邮箱已经存在");
		}
		//密码加密
		String plain = user.getPassword();
		String md5 = MD5.next(plain);
		user.setPassword(md5);
		//数据库添加用户
		userService.addUser(user);
		
		//生成用户t票
		Ticket ticket = TicketUtils.next(user.getId());
		
		//数据库添加t票
		ticketService.addTicket(ticket);
		
		ConcurrentUtils.setHost(user);
		
		return ticket.getTicket();
	}
	
	/**
	 * 登录 先检查邮箱、密码  在重新计算t票
	 * @param email
	 * @param password
	 * @return  返回最新的t
	 */
	public String login(String email, String password) {
		User user = userService.getUser(email);
		
		//检查登录信息
		if (user == null) 
			throw new LoginRegisterException("邮箱不存在");
		if (!StringUtils.equals(MD5.next(password), user.getPassword()))
			throw new LoginRegisterException("密码不正确");
		
		//检查ticket
		Ticket t = ticketService.getTicket(user.getId());
			//t 判断 有无 。 无则生成一个
		if (t ==null) {
			t = TicketUtils.next(user.getId());
			ticketService.addTicket(t);
			return t.getTicket();
		}
		// t 是否过期
		if (t.getExpiredAt().before(new Date())) {
			//删除
			ticketService.deleteTicket(t.getId());
		}
		
		t = TicketUtils.next(user.getId());
		ticketService.addTicket(t);
		
		ConcurrentUtils.setHost(user);
		return t.getTicket();
	}

	/**
	 * 用户退出 删除数据库t
	 * @param t
	 */
	public void logout(String t) {
		ticketService.deleteTicket(t);
	}
	
	
}

































