package com.nowcoder.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowcoder.project.dao.TicketDAO;
import com.nowcoder.project.model.Ticket;

@Service
public class TicketService {

	@Autowired
	private TicketDAO ticketDAO;

	public void addTicket(Ticket t) {
		ticketDAO.addTicket(t);
	}

	public Ticket getTicket(int uid) {
		return ticketDAO.selctByUserId(uid);
	}

	public void deleteTicket(int tid) {
		ticketDAO.deleteTickedById(tid);
	}

	public void deleteTicket(String t) {
		 ticketDAO.deleteTicket(t);
	}

	public Ticket getTicket(String t) {
		return ticketDAO.selectByTicket(t);
	}

}
