package com.nowcoder.project.utils;

import org.joda.time.DateTime;

import com.nowcoder.project.model.Ticket;

public class TicketUtils {

	public static Ticket next(int uid) {
		Ticket ticket = new Ticket();
		ticket.setTicket(UuidUtils.next());
		ticket.setUserId(uid);
		
		//设置t票过期时间
		DateTime expiredTime = new DateTime();
		expiredTime = expiredTime.plusMonths(3);
		ticket.setExpiredAt(expiredTime.toDate());
		return ticket;
	}

}
