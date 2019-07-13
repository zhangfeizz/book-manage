package com.nowcoder.project.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.nowcoder.project.model.Ticket;
import com.nowcoder.project.model.User;
import com.nowcoder.project.service.TicketService;
import com.nowcoder.project.service.UserService;
import com.nowcoder.project.utils.ConcurrentUtils;
import com.nowcoder.project.utils.CookieUtils;

@Component
public class HostInfoInterceptor implements HandlerInterceptor {
	@Autowired
	private TicketService ticketService;

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String t = CookieUtils.getCookie("t", request);
		if (!StringUtils.isEmpty(t)) {
			Ticket ticket = ticketService.getTicket(t);
			if (ticket != null && ticket.getExpiredAt().after(new Date())) {
				User host = userService.getUser(ticket.getUserId());
				ConcurrentUtils.setHost(host);
			}
		}
		return true;
	}
	
	
}
