package com.nowcoder.project.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.nowcoder.project.model.Ticket;
import com.nowcoder.project.service.TicketService;
import com.nowcoder.project.utils.CookieUtils;
@Component
public class LoginInterceptor implements HandlerInterceptor{
	@Autowired
	private TicketService ticketService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//没有t票 去登陆
	 	String t = CookieUtils.getCookie("t" ,request);
		if (StringUtils.isEmpty(t)) {
			response.sendRedirect("user/login");
			return false;
		}
		
		//无效t票 登录
		Ticket ticket = ticketService.getTicket(t);
		if (ticket == null) {
			response.sendRedirect("user/login");
			return false;
		}
		
		//过期他票 登录
		if (ticket.getExpiredAt().before(new Date())) {
			response.sendRedirect("user/login");
			return false;
		}
		return true;
	}
}
