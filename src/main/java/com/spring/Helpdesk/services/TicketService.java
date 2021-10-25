package com.spring.Helpdesk.services;

import java.util.List;

import com.spring.Helpdesk.models.Ticket;

public interface TicketService {

	public List<Ticket> findAll();
	public Ticket create(Ticket ticket);
	public boolean delete(long id);
	public boolean update(long id, Ticket ticket);
	public Ticket show(long id);
	
}
